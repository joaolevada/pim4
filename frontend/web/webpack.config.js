/* eslint-disable */
const path = require('path');
// const ExtractTextPlugin = require('extract-text-webpack-plugin');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

let plugins = []

plugins.push(new webpack.ProvidePlugin({
    'doc': 'jquery/dist/jquery.js',
}));

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
        vendor: ['./index.js',
            './src/routes/rotas.js',
            './src/components/card/card.component.js',
            './src/components/breadcrumb/breadcrumb.component.js',
            './src/views/novoCliente/novoCliente.js',
            './src/views/abrirChamado/formulario/novoChamado.component.js'
        ]
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
        publicPath: path.resolve(__dirname, '/dist/')
    },
    devServer: {
        // contentBase: path.resolve(__dirname, './'),
        publicPath: path.resolve(__dirname, '/dist/'),
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
        }

        ]
    },

    plugins
}