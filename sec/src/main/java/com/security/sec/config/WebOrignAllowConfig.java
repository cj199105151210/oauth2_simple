package com.security.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *  使用Filter 处理跨域请求，即CORS（跨来源资源共享）。
 */
@Configuration
public class WebOrignAllowConfig {

    /**
     * 设置跨域请求参数，处理跨域请求
     * @return
     */
    @Bean
    public CorsFilter crosFilter(){
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setMaxAge(3600L);         // 预检请求的有效期，单位为秒。
        corsConfiguration.setAllowCredentials(true);// 是否支持安全证书
        corsConfiguration.addAllowedOrigin("*");    // 允许跨域访问的域名
        corsConfiguration.addAllowedHeader("*");    // 请求头
        corsConfiguration.addAllowedMethod("*");    // 请求方法
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}

/**
 * 1, 简介
 * CORS是一个W3C标准，全称是"跨域资源共享"（Cross-origin resource sharing）。
 * 它允许浏览器向跨源(协议 + 域名 + 端口)服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制
 *
 *CORS需要浏览器和服务器同时支持。它的通信过程，都是浏览器自动完成，不需要用户参与。
 * 对于开发者来说，CORS通信与同源的AJAX通信没有差别，代码完全一样。
 * 浏览器一旦发现AJAX请求跨源，就会自动添加一些附加的头信息，有时还会多出一次附加的请求，但用户不会有感觉。
 * 因此，实现CORS通信的关键是服务器。只要服务器实现了CORS接口，就可以跨源通信。
 *
 *
 *2, 基本流程
 * 浏览器将CORS请求分成两类：简单请求（simple request）和非简单请求（not-so-simple request）。
 * 浏览器发出CORS简单请求，只需要在头信息之中增加一个Origin字段。
 * 浏览器发出CORS非简单请求，会在正式通信之前，增加一次HTTP查询请求，称为"预检"请求（preflight）。
 * 浏览器先询问服务器，当前网页所在的域名是否在服务器的许可名单之中，以及可以使用哪些HTTP动词和头信息字段。
 * 只有得到肯定答复，浏览器才会发出正式的XMLHttpRequest请求，否则就报错。
 *
 *
 * // 请求方法
 * corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
 * corsConfiguration.addAllowedMethod(HttpMethod.POST);
 * corsConfiguration.addAllowedMethod(HttpMethod.GET);
 * corsConfiguration.addAllowedMethod(HttpMethod.PUT);
 * corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
 * corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
 *
 * 4, 配置项解释
 * 4.1 Access-Control-Allow-Origin
 * 该字段必填。它的值要么是请求时Origin字段的具体值，要么是一个*，表示接受任意域名的请求。
 *
 * 4.2 Access-Control-Allow-Methods
 * 该字段必填。它的值是逗号分隔的一个具体的字符串或者*，表明服务器支持的所有跨域请求的方法。
 * 注意，返回的是所有支持的方法，而不单是浏览器请求的那个方法。这是为了避免多次"预检"请求。
 *
 * 4.3 Access-Control-Expose-Headers
 * 4.3 该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：
 * Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。
 * 如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。
 *
 * 4.4 Access-Control-Allow-Credentials
 * 该字段可选。它的值是一个布尔值，表示是否允许发送Cookie.默认情况下，不发生Cookie，即：false。
 * 对服务器有特殊要求的请求，比如请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json，这个值只能设为true。
 * 如果服务器不要浏览器发送Cookie，删除该字段即可。
 *
 * 4.5 Access-Control-Max-Age
 * 该字段可选，用来指定本次预检请求的有效期，单位为秒。在有效期间，不用发出另一条预检请求。
 *
 * 注意: CORS请求发送Cookie时，Access-Control-Allow-Origin只能是与请求网页一致的域名。
 * 同时，Cookie依然遵循同源政策，只有用服务器域名设置的Cookie才会上传，
 * 其他域名的Cookie并不会上传，且（跨源）原网页代码中的document.cookie也无法读取服务器域名下的Cookie。
 *
 */
