//可以修改访问后端接口地址
'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
    NODE_ENV: '"development"',
    //BASE_API: '"https://easy-mock.com/mock/5950a2419adc231f356a6636/vue-admin"', //具体的接口地址
    BASE_API: '"http://localhost:9001"', //请求的后端具体地址(使用nginx反向代理，此处访问nginx的端口号)
})