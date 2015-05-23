module.exports = {
    buildDir:'./build',

    vendorSources: [
        './vendor/angular/angular.js',
        './vendor/angular-animate/angular-animate.js',
        './vendor/angular-i18n/angular-locale_hu-hu.js',
        './vendor/angular-ui-router/release/angular-ui-router.js',
        './vendor/angular-foundation/mm-foundation-tpls.js',
        './vendor/foundation/css/foundation.css',
        './vendor/foundation/css/normalize.css',
        './vendor/angular-smart-table/dist/smart-table.js',
        './vendor/angular-toastr/dist/angular-toastr.tpls.js',
        './vendor/angular-toastr/dist/angular-toastr.css',
        './vendor/isteven-angular-multiselect/isteven-multi-select.js',
        './vendor/isteven-angular-multiselect/isteven-multi-select.css'
    ],

    jsSources: [
        './src/**/*.js', '!src/**/*.spec.js'
    ],

    templateSources: [
        './src/**/*.tpl.html'
    ],

    lessSources: [
        './src/**/*.less'
    ],

    indexHtml: './src/index.html'
};
