var gulp = require('gulp');
var concat = require('gulp-concat');
var del = require('del');
var gutil = require('gulp-util');
var inject = require('gulp-inject');
var less = require('gulp-less');
var series = require('stream-series');
var watch = require('gulp-watch');
var config = require('./build.config.js');


gulp.task('clean', function(cb) {
    return del(config.buildDir, cb);
});

gulp.task('copy', function() {
    // The base option sets the relative root for the set of files, preserving the folder structure
    var vendorStream = gulp.src(config.vendorSources, { base: './' });
    var appStream = gulp.src(config.appSources, { base: './' });

    series(vendorStream, appStream)
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('less', function() {
    gulp.src(config.lessSources)
        .pipe(concat('app.less'))
        .pipe(less())
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('index', function() {
    var vendorStream = gulp.src(config.vendorSources, {read: false});
    var appStream = gulp.src(config.appSources, {read: false});

    gulp.src(config.indexHtml)
        .pipe(inject(vendorStream, {name: 'vendor', addRootSlash: false}))
        .pipe(inject(appStream, {name: 'app', addRootSlash: false}))
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('build', ['copy', 'less', 'index']);

gulp.task('watch', ['build'], function() {
    var watchFor = []
        .concat(config.appSources)
        .concat(config.lessSources)
        .concat([config.indexHtml]);

    var watcher = gulp.watch(watchFor, ['build']);
    watcher.on('change', function(event) {
        gutil.log(gutil.colors.magenta(event.path) +  ' ' + event.type + ', building now...');
    });
});

gulp.task('default', ['watch']);
