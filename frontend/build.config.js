
module.exports = {
    buildDir:'./build',

    vendorSources: [
        './vendor/angular/angular.js',
        './vendor/angular-ui-router/release/angular-ui-router.js',
        './vendor/angular-foundation/mm-foundation-tpls.js',
        './vendor/foundation/css/foundation.css',
        './vendor/foundation/css/normalize.css',
        './vendor/angular-smart-table/dist/smart-table.js'
    ],

    appSources: [
        './src/**/*.js', '!src/**/*.spec.js',
        './src/**/*.tpl.html'
    ]
};
