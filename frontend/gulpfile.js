var gulp = require('gulp');
var concat = require('gulp-concat');
var del = require('del');
var ext_replace = require('gulp-ext-replace');
var gutil = require('gulp-util');
var gulpif = require('gulp-if');
var headerfooter = require('gulp-headerfooter');
var inject = require('gulp-inject');
var less = require('gulp-less');
var minifycss = require('gulp-minify-css');
var series = require('stream-series');
var uglify = require('gulp-uglify');
var watch = require('gulp-watch');
var argv = require('yargs').argv;
var config = require('./build.config.js');


gulp.task('clean', function(cb) {
    return del(config.buildDir, cb);
});

gulp.task('vendor', ['vendor-js', 'vendor-css']);

gulp.task('vendor-js', function() {
    // The base option sets the relative root for the set of files, preserving the folder structure
    return gulp.src(config.vendorJs, { base: './' })
        .pipe(gulpif(argv.production, concat('vendor.js')))
        .pipe(gulpif(argv.production, uglify()))
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('vendor-css', function() {
    return gulp.src(config.vendorCss, { base: './' })
        .pipe(gulpif(argv.production, concat('vendor.css')))
        .pipe(gulpif(argv.production, minifycss()))
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('app', ['js', 'templates', 'less']);

gulp.task('js', function() {
    return gulp.src(config.jsSources, { base: './' })
        .pipe(headerfooter.header('./js_header.txt'))
        .pipe(headerfooter.footer('./js_footer.txt'))
        .pipe(require('gulp-ng-annotate')({single_quotes: true}))
        .pipe(gulpif(argv.production, concat('app.js')))
        .pipe(gulpif(argv.production, uglify()))
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('templates', function() {
    return gulp.src(config.templateSources, { base: './' })
        .pipe(gulp.dest(config.buildDir));
});

gulp.task('less', function() {
    return gulp.src(config.lessSources, { base: './' })
        .pipe(gulpif(argv.production, concat('app.less')))
        .pipe(less())
        .pipe(gulpif(argv.production, concat('app.css')))
        .pipe(gulpif(argv.production, minifycss()))
        .pipe(gulp.dest(config.buildDir));
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

    if (argv.production) {
        vendor = gulp.src(['vendor.css', 'vendor.js'], {read: false, cwd: config.buildDir});
        app = gulp.src(['app.css', 'app.js'], {read: false, cwd: config.buildDir});
    }

    return gulp.src(config.indexHtml)
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
