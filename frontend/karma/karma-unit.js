module.exports = {

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',

    // list of files / patterns to load in the browser
    files: [
      './vendor/angular-mocks/angular-mocks.js'
    ],

    // list of files to exclude
    exclude: [

    ],

    frameworks: [ 'jasmine' ],
    plugins: [ 'karma-jasmine', 'karma-babel-preprocessor', 'karma-phantomjs-launcher', 'karma-spec-reporter' ],
    preprocessors: {
        'src/**/*.js': ['babel'],
        'src/**/*.spec.js': ['babel']
    },
    reporters: ['spec'],
    port: 9018,
    runnerPort: 9100,
    autoWatch: false,
    singleRun: true,
    //logLevel: 'LOG_DEBUG',
    browsers: [
      'PhantomJS'
    ],

    captureTimeout: 60000,
    browserDisconnectTimeout : 10000,
    browserDisconnectTolerance : 1,
    browserNoActivityTimeout : 60000
};

