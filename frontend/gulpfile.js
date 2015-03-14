var gulp = require('gulp');
var del = require('del');
var inject = require('gulp-inject');
var series = require('stream-series');
var watch = require('gulp-watch');
var config = require('./build.config.js');


gulp.task('clean', function(cb) {
    return del(config.buildDir, cb);
});

gulp.task('copy', ['clean'], function() {
    // The base option sets the relative root for the set of files, preserving the folder structure
    var vendorStream = gulp.src(config.vendorSources, { base: './' });
    var appStream = gulp.src(config.appSources, { base: './' });

    series(vendorStream, appStream)
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('index', ['clean'], function() {
    var vendorStream = gulp.src(config.vendorSources, {read: false});
    var appStream = gulp.src(config.appSources, {read: false});

    gulp.src('./src/index.html')
        .pipe(inject(vendorStream, {name: 'vendor'}))
        .pipe(inject(appStream, {name: 'app'}))
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('build', ['clean', 'copy', 'index']);

gulp.task('watch', function() {
    var watchFor = config.appSources.slice();
    watchFor.push('./src/index.html');
    gulp.watch(watchFor, ['build']);
});

gulp.task('default', ['watch']);
