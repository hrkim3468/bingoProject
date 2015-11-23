module.exports = function(grunt) {
  require('time-grunt')(grunt);
  require("load-grunt-tasks")(grunt, { pattern: ["grunt-*", "assemble"] });

  var profile = grunt.option("profile", "local");

  
  // ========================================================
  // 환경설정
  // ========================================================
  var environment = {
    profile: profile,
    assetPath: {
      css: 	"/css",
      js: 	"/js",
      lib: 	"/libs"
    }
  };


  // ========================================================
  // 명령어 정의
  // ========================================================  
  grunt.initConfig({
    
	// 패키지 설정 정보 정의
	pkg: grunt.file.readJSON("package.json"),
    
	// 자바스크립트 문법 검사 (http://jshint.com/)
	jshint: {
      options: {
        jshintrc: true
      },
      assets: {
        src: "src/assets/js/**/*.js"
      }
    },
    
    // 자바스크립트 복잡도 검사 후 레포트 생성 (https://github.com/es-analysis/plato)
    plato: {
      options: {
        jshint: grunt.file.readJSON(".jshintrc")
      },
      scripts: {
        files: {
          "reports": ["src/assets/js/**/*.js"]
        }
      }
    },
    
    // Temp 디렉토리 삭제
    clean: {
      main: ["reports", "dist", ".tmp"]
    },

    // 배포를 위한 전처리 작업 (스크립트 압축, Merge)
    copy: {
    	main: {
    		expand: true,
    		cwd: 'src/apps/',
    		src: '**', 
    		dest: 'dist/apps/',
    		flatten: true,
    		filter: 'isFile'
    	}
    },
    useminPrepare: {
    	main: {
    		src: ["src/apps/**/*.html"]
    	},
    	options: {
    		dest: 'dist/apps'
    	}
    },    
    usemin: {
      html: 'dist/apps/**/*.html'
    },

    
    // [옵션] Static 리소스명을 Hash 타입으로 변환 
    filerev: {
	    options: {
	      algorithm: "md5",
	      length: 8
	    },
	    dist: {
	      src: ["dist/assets/js/**/*.js", "dist/assets/css/**/*.css"]
	    }
	},    
        

    // [개발용] 경량 웹서버
    connect: {
      main: {
        options: {
          port: 31589,
          protocol: "http",
          base: {
            path: "src/apps/",
            options: {
              index: 'hello1.html'
            }
          },
          middleware: function(connect, options, middlewares) {
            middlewares.unshift(connect.static("src/"));

            return middlewares;
          }
        }
      }
    },
    
    // [개발용] 변경사항 웹서버 자동 반영
    watch: {
      options: {
    	  interrupt: true,
    	  livereload: true
      },
      js: {
    	  files: ['src/assets/js/**/*.js'],
    	  tasks: []
      },
      css: {
          files: ['src/assets/css/**/*.css'],
          tasks: []
      }
    }
    
  });


  // ========================================================
  // 실행 Task 정의
  // ========================================================

  // 로컬 웹서버 실행 (Local에서 개발시 사용)
  grunt.registerTask("local", ["connect", "watch"]);
  
  // 리얼용 빌드
  grunt.registerTask("real", ["clean", "jshint", "copy:main", "useminPrepare", "concat:generated", "cssmin:generated", "uglify:generated", "usemin"]);

  // 리얼용 빌드 (HTML5 AppCache)
  grunt.registerTask("real2", ["clean", "jshint", "copy:main", "useminPrepare", "concat:generated", "cssmin:generated", "uglify:generated", "usemin", "filerev"]);
};










