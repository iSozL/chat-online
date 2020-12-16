const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const SpeedMeasurePlugin = require('speed-measure-webpack-plugin')
const smp = new SpeedMeasurePlugin()
const ProgressBarPlugin = require('progress-bar-webpack-plugin')
const webpack = require('webpack')
module.exports = smp.wrap(
  {
    entry: './src/index.tsx',
    module: {
      rules: [
        {
          test: /\.tsx?$/,
          use: [
            {
              loader: 'babel-loader',
              options: {
                babelrc: true,
              }
            }
          ],
          exclude: /node_modules/,
        },
        {
          test: /\.s[ac]ss$/i,
          use: [
            'style-loader',
            'css-loader',
            'sass-loader',
          ],
        },
        {
          test: /\.less$/,
          use: [
            {
              loader: 'style-loader',
            },
            {
              loader: 'css-loader',
            },
            {
              loader: 'less-loader',
            },
          ],
        },
        {
          test: /\.css$/,
          use: [
            {
              loader: 'style-loader'
            },
            {
              loader: 'css-loader'
            }
          ]
        },
        {
          test: /\.(png|jpg|svg)$/,
          use: [
            {
              loader: 'url-loader',
              options: {
                esModule: false,
              }
            }
          ] 
        }
      ]
    },
    resolve: {
      extensions: ['.tsx', '.ts', '.js']
    },
    output: {
      path: path.resolve(__dirname, "dist"),
      filename: "build.js"
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: './dist/index.html'
      }),
      new ProgressBarPlugin(),
      new webpack.DllPlugin({
        context: __dirname,
        name: "_dll_[name]_libary",
        path: path.join(__dirname, 'manifest.json')
      })
    ],
    devServer: {
      contentBase: path.join(__dirname, "dist"),
      historyApiFallback: true,
      hot: true
    },
  }
)