package kr.or.javacafe.bingo.config.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 빙고 프로젝트 공통라이브러리(bingo-core.jar)에 존재하는 컴포넌트들을
 * POJO로 생성하기 위해 @ComponentScan 처리해준다. 
 * 
 * @author hrkim
 *
 */
@Configuration
@ComponentScan("kr.or.javacafe.core")
public class CoreInitializer {

}
