/* eslint-disable */
const path = require('path');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const optimization = {
    splitChunks: {
        cacheGroups: {
            vendors: {
                test: /[\\/]node_modules[\\/]/,
                chunks: 'all',
                priority: 1
            }
        }
    }
}

let plugins = []

plugins.push(new HtmlWebpackPlugin({
    hash: true,
    minify: {
        html5: true,
        collapseWhitespace: false,
        removeComments: true
    },
    filename: 'index.html',
    template: __dirname + '/main.html'
}))

plugins.push(new webpack.ProvidePlugin({
    'doc': 'jquery/dist/jquery.js',
}))

plugins.push(new MiniCssExtractPlugin({
    filename: 'bundle.css',
}))

module.exports = {
    mode: 'development',
    entry: {
        app: [
            './src/app.js',
            //Sass das views
            './src/views/home/home.scss',
            './src/assets/scss/style.scss',
        ],
        vendors: ['./index.js',
            './src/routes/rotas.js',
            // './src/components/card/card.component.js',
            // './src/components/breadcrumb/breadcrumb.component.js',
            // './src/views/novoCliente/novoCliente.js',
            // './src/views/abrirChamado/formulario/novoChamado.component.js'
        ]
    },

    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
        // publicPath: path.resolve(__dirname, '/dist/')
    },

    optimization: optimization,

    devServer: {
        contentBase: path.resolve(__dirname, 'dist'),
        // publicPath: path.resolve(__dirname, '/dist/'),
    },

    module: {
        rules: [{
            test: /\.scss$/,
            exclude: /node_modules/,
            use: ['style-loader', MiniCssExtractPlugin.loader, 'css-loader', 'postcss-loader', 'sass-loader']

        },
        {
            test: /\.js$/,
            exclude: /node_modules/,
            use: {
                loader: 'babel-loader'
            }
        },
        {
            test: /\.(html)$/,
            use: {
                loader: 'html-loader',

            }
        },
        { 
            test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/, 
            loader: 'url-loader?limit=10000&mimetype=application/font-woff' 
        },
        { 
            test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, 
            loader: 'url-loader?limit=100&mimetype=application/octet-stream'
        },
        { 
            test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, 
            loader: 'file-loader' 
        },
        { 
            test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, 
            loader: 'url-loader?limit=100&mimetype=image/svg+xml' 
        }   
        ]
    },

    plugins
}