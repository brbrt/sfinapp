var webpack = require('webpack');
var path = require('path');
var copyWebpackPlugin = require('copy-webpack-plugin');

var BUILD_DIR = path.resolve(__dirname, 'build');
var APP_DIR = path.resolve(__dirname, 'src');

var config = {
  entry: APP_DIR + '/app.js',
  output: {
    path: BUILD_DIR,
    filename: 'bundle.js'
  },
  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'babel',
        query: {
          presets: ['es2015'],
          cacheDirectory: true
        }
      },
      {
        test: /\.html$/,
        loader: "ng-cache?prefix=[dir]/[dir]"
      },
      {
        test: /\.less$/,
        loader: "style!css!less"
      },
      {
        test: /\.css$/,
        loader: "style-loader!css-loader"
      }
    ]
  },
  plugins: [
    new copyWebpackPlugin([
      {from: 'src/index.html' }
    ])
  ],
  resolve: {
    extensions: ['', '.js', '.json', '.coffee']
  }
};

module.exports = config;
