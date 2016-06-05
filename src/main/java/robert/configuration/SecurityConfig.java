package robert.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by robert on 25.03.16.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        httpSecurity.csrf().disable(); // post methods work now TODO csrf protection
        httpSecurity.headers().frameOptions().disable();


        // CSRF - http://www.codesandnotes.be/2015/07/24/angularjs-web-apps-for-spring-based-rest-services-security-the-server-side-part-2-csrf/
//        httpSecurity.csrf().requireCsrfProtectionMatcher(
//                new AndRequestMatcher(
//                        // Apply CSRF protection to all paths that do NOT match the ones below
//
//                        // We disable CSRF at login/logout, but only for OPTIONS methods
//                        new NegatedRequestMatcher(new AntPathRequestMatcher("/login**/*//**", HttpMethod.OPTIONS.toString())),
//                        new NegatedRequestMatcher(new AntPathRequestMatcher("/register**/*//**", HttpMethod.OPTIONS.toString())),
//                        new NegatedRequestMatcher(new AntPathRequestMatcher("/data**/*//**", HttpMethod.OPTIONS.toString())),
//
//                        new NegatedRequestMatcher(new AntPathRequestMatcher("/rest**/*//**", HttpMethod.GET.toString())),
//                        new NegatedRequestMatcher(new AntPathRequestMatcher("/rest/open**/*//**"))
//                )
//        );
//
    }
}
