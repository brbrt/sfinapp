module.exports = {
    buildDir:'./build',

    vendorJs: [
        './node_modules/angular/angular.js',
        './node_modules/angular-animate/angular-animate.js',
        './node_modules/angular-aria/angular-aria.js',
        './node_modules/angular-sanitize/angular-sanitize.js',
        './node_modules/angular-i18n/angular-locale_hu-hu.js',
        './node_modules/angular-material/angular-material.js',
        './node_modules/angular-ui-router/release/angular-ui-router.js',
        './node_modules/angular-mass-autocomplete/massautocomplete.js',
        './node_modules/angular-smart-table/dist/smart-table.js',
        './node_modules/angular-toastr/dist/angular-toastr.tpls.js',
        './node_modules/isteven-angular-multiselect/isteven-multi-select.js',
        './node_modules/moment/moment.js'
    ],

    vendorCss: [
        './node_modules/angular-material/angular-material.css',
        './node_modules/angular-mass-autocomplete/massautocomplete.theme.css',
        './node_modules/angular-toastr/dist/angular-toastr.css',
        './node_modules/isteven-angular-multiselect/isteven-multi-select.css'
    ],

    jsSources: [
        './src/**/*.js', '!./src/**/*.spec.js'
    ],

    testSources: [
        './src/**/*.spec.js'
    ],

    templateSources: [
        './src/**/*.tpl.html'
    ],

    lessSources: [
        './src/**/*.less'
    ],

    indexHtml: './src/index.html'
};
