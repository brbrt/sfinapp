var gulp = require('gulp');
var concat = require('gulp-concat');
var del = require('del');
var ext_replace = require('gulp-ext-replace');
var gutil = require('gulp-util');
var inject = require('gulp-inject');
var less = require('gulp-less');
var series = require('stream-series');
var watch = require('gulp-watch');
var config = require('./build.config.js');


gulp.task('clean', function(cb) {
    return del(config.buildDir, cb);
});

gulp.task('vendor', ['vendor-js', 'vendor-css']);

gulp.task('vendor-js', function() {
    // The base option sets the relative root for the set of files, preserving the folder structure
    gulp.src(config.vendorJs, { base: './' })
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('vendor-css', function() {
    gulp.src(config.vendorCss, { base: './' })
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('app', ['js', 'templates', 'less']);

gulp.task('js', function() {
    gulp.src(config.jsSources, { base: './' })
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('templates', function() {
    gulp.src(config.templateSources, { base: './' })
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('less', function() {
    gulp.src(config.lessSources)
        //.pipe(concat('app.less'))
        .pipe(less())
        .pipe(gulp.dest(config.buildDir + '/src'));
});

gulp.task('index', ['vendor', 'app'], function() {
    var vendor = series(
        gulp.src(config.vendorJs, {read: false}),
        gulp.src(config.vendorCss, {read: false})
    );
    var app = series(
        gulp.src(config.jsSources, {read: false}),
        gulp.src(config.lessSources, {read: false})
            .pipe(ext_replace('.css', '.less'))
    );

    gulp.src(config.indexHtml)
        .pipe(inject(vendor, {name: 'vendor', addRootSlash: false}))
        .pipe(inject(app, {name: 'app', addRootSlash: false}))
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('build', ['vendor', 'app', 'index']);

gulp.task('watch', ['build'], function() {
    var watchFor = []
        .concat(config.jsSources)
        .concat(config.templateSources)
        .concat(config.lessSources)
        .concat([config.indexHtml]);

    var watcher = gulp.watch(watchFor, ['build']);
    watcher.on('change', function(event) {
        gutil.log(gutil.colors.magenta(event.path) +  ' ' + event.type + ', building now...');
    });
});

gulp.task('default', ['watch']);
