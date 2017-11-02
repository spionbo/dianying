let webpack = require('webpack'),
    path = require('path'),
    HtmlWebpackPlugin = require('html-webpack-plugin'),
    CleanPlugin = require('clean-webpack-plugin'),
    ExtractTextPlugin = require("extract-text-webpack-plugin"),
    CommonsChunkPlugin = require("webpack/lib/optimize/CommonsChunkPlugin"),
    build = process.env.NODE_ENV === 'build',
    local = '',
    http = '';
const postcss = {
    plugins: [
        require('postcss-partial-import'),
        require('autoprefixer'),
        /*require('postcss-pxtorem')({
            replace: true,
            propList: ['font', 'font-size', 'line-height', 'box-shadow', 'letter-spacing', 'perspective', 'transform', '-webkit-perspective', '-webkit-transform', 'max-width', 'min-width', 'max-height', 'min-height', 'margin', 'margin-left', 'margin-right', 'margin-bottom', 'margin-top', 'padding', 'padding-left', 'padding-right', 'padding-bottom', 'padding-top', 'border', 'border-left', 'border-right', 'border-bottom', 'border-top', 'width', 'height', 'background-position', 
                'top','left','bottom','right',
                'border-radius'],
        }),*/
        require('postcss-extend'),
        require('precss'),
    ]
};
module.exports = {
    devServer: {
        historyApiFallback: true,
        hot: true,
        inline: true,
        contentBase: path.join(__dirname, "src"),
        compress: true,
        progress: true,
        port: 7000,
        proxy: {
            '\*': {
                target: 'http://192.168.0.108:8080',
                secure: false
            }
        } 
    },
    entry: {
        app: './src/main.js'
        /*app: [path.join(__dirname,'src','main.js'),
            'webpack-dev-server/client?http://localhost:8080/'
        ]*/
    },
    externals: {
        '$': 'window.$',
    },
    output: {
        path: path.join(__dirname, build ? output+'/build' : "/web"),
        //publicPath: build ? http : local,
        filename: "[name].js?name=[hash]",
        chunkFilename: '[chunkhash:8]_chunk.js?name=[hash]',
    },
    module: {
        rules: [
        {
            test: /\.css$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: [
                    'css-loader',
                    {
                        loader: 'postcss-loader',
                        options : postcss
                    }
                ]
            })
        },
        {
            test: /\.js$/,
            enforce: "pre",
            loader: "babel-loader",
            include: [ // 只去解析运行目录下的 src 和 demo 文件夹
                path.join(process.cwd(), './src'),
            ],
            exclude: function(path) {
                // 路径中含有 node_modules 的就不去解析。
                var isNpmModule = !!path.match(/node_modules/);
                return isNpmModule;
            },
            options: {
                compact: false
            }
        }, {
            test: /\.vue$/,
            loader: 'vue-loader',
            include: [ // 只去解析运行目录下的 src 和 demo 文件夹
                path.join(process.cwd(), './src'),
            ],
            exclude: /node_modules/,
            options: {
                extractCSS: true,
                postcss: postcss.plugins,
            }
        }, {
            test: /\.(png|jpg|gif|svg|woff|eot|ttf|woff2)$/,
            use: [{
                loader: 'url-loader',
                options: {
                    limit: 8000, //图片大小压缩成base64 
                    name: 'images/[path][name].[ext]?[hash:8]' //图片输出路径
                }
            }]
        }, ],
    },
    resolve: {
        modules: [
            path.join(__dirname, "/"), "node_modules"
        ],
        alias: {
            Zepto : 'src/plug/zepto.min',
            global: 'src/common/global',
            Vue: 'src/plug/vue.min',
            VueRouter: 'src/plug/vue-router.min',
            Vuex: 'src/plug/vuex.min',
        },
        extensions: ['.js', '.json', '.scss', '.sass', '.vue', '.jsx', '.css'],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve('./src/', 'index.html'),
            inject: true,
            filename: 'index.html',
            minify: {
                removeComments: true, //移除HTML中的注释
                collapseWhitespace: true //删除空白符与换行符
            }
        }),
        new webpack.optimize.ModuleConcatenationPlugin(),
        new webpack.ProvidePlugin({
            Promise: 'es6-promise',
            T: 'global',
            Vue: 'Vue',
            VueRouter: 'VueRouter',
            Vuex: 'Vuex',
            Zepto: 'Zepto'
        }),
        new ExtractTextPlugin({
            filename: './[name].css?[hash]',//[id]
            allChunks: true
        }),
        //new webpack.optimize.OccurrenceOrderPlugin(),
        //new webpack.HotModuleReplacementPlugin()
    ]
};
if (build) {
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            },
            sourceMap: false
        }),
        //new webpack.optimize.OccurrenceOrderPlugin(),
        //new CleanPlugin('./ftp')
    ])
} else {
    module.exports.devtool = '#source-map'
}
