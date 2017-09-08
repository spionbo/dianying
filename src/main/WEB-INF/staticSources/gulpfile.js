let gulp = require('gulp'),
    watch = require('gulp-watch'),
    uglify = require('gulp-uglify'),
    babel = require('gulp-babel'),

    postcss = require('gulp-postcss'),
    precss = require('precss'),
    cssmin = require('gulp-minify-css'),
    partialimport = require('postcss-partial-import'),
    autoprefixer = require('autoprefixer'),
    mixins = require('postcss-sassy-mixins'),
    cssnano = require('gulp-cssnano'),
    simpleExtend = require('postcss-extend'),


    rev = require('gulp-rev-append'),
    htmlmin = require('gulp-htmlmin'),
    concat = require('gulp-concat'),
    inlinesource = require('gulp-inline-source'),
    clean = require('gulp-clean'),

    imagemin = require('gulp-imagemin');


input = ['./index.html']
gulp.task('html',function () {
    // html
    let options = {
        compress: false
    };
    //index.html压缩
    gulp.src(input)
        .pipe(inlinesource(options))
        .pipe(gulp.dest('./'));

});

gulp.task('css', function () {
    return gulp.src([
            'style/**/*.css','style/*.css'
        ])
        .pipe(postcss([
                partialimport,
                mixins,
                precss,
                cssnano,
                simpleExtend,
                autoprefixer({
                    "browserslist": {
                        "production": [
                            '> 1%',
                            'Last 2 versions',
                            'IE 9',
                            'Firefox > 20'
                        ],
                        "development": [
                          "last 1 version"
                        ]
                      }
                    }
                )
            ])
        )
        //.pipe(cssver())
        .pipe(cssmin({
            advanced: false,//类型：Boolean 默认：true [是否开启高级优化（合并选择器等）]
            compatibility: '*',//保留ie7及以下兼容写法 类型：String 默认：''or'*' [启用兼容模式； 'ie7'：IE7兼容模式，'ie8'：IE8兼容模式，'*'：IE9+兼容模式]
            keepBreaks: false,//类型：Boolean 默认：false [是否保留换行]
            keepSpecialComments: '*'
            //保留所有特殊前缀 当你用autoprefixer生成的浏览器前缀，如果不加这个参数，有可能将会删除你的部分前缀
        }))
        .pipe( gulp.dest('../static/style/') );
});


gulp.task('img', function () {
    gulp.src([
            'images/*.{png,jpg,gif,ico}'

        ])
        .pipe(imagemin({
            optimizationLevel: 5, //类型：Number  默认：3  取值范围：0-7（优化等级）
            progressive: true, //类型：Boolean 默认：false 无损压缩jpg图片
            interlaced: true, //类型：Boolean 默认：false 隔行扫描gif进行渲染
            multipass: true //类型：Boolean 默认：false 多次优化svg直到完全优化
        }))
        .pipe(gulp.dest('../static/images'));
});

gulp.task('js', function () {
    const options = {
        mangle: false,//类型：Boolean 默认：true 是否修改变量名,
        compress: true,//类型：Boolean 默认：true 是否完全压缩
        preserveComments: 'license',//'license', //保留所有注释
    };
    gulp.src([
            'js/*.js','js/**/*.js',
            "!gulpfile.js",
            "js/common/*.js",
            "!js/plug/*.js",
        ])
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(uglify(options))
        .pipe(gulp.dest('../static/js'));
});

gulp.task('all', function(){
    gulp.run('css','html', 'img', 'js');
});

gulp.task('watch',function(){
    /*gulp.watch('style/!*.css',['css']);
    gulp.watch('style/!**!/!*.css',['css']);*/
    gulp.watch('images/*.{png,jpg,gif,ico}',['img']);
    gulp.watch('style/*.css',['css']);
    gulp.watch(['js/*.js','js/**/*.js'],['js']);
});