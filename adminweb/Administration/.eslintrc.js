// https://eslint.org/docs/user-guide/configuring

module.exports = {
    root: true,
    parserOptions: {
        parser: 'babel-eslint'
    },
    env: {
        browser: true,
    },
    extends: [
        // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
        // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
        'plugin:vue/essential',
        // https://github.com/standard/standard/blob/master/docs/RULES-en.md
        'standard'
    ],
    // required to lint *.vue files
    plugins: [
        'vue'
    ],
    // add your custom rules here
    rules: {
        // allow async-await
        'generator-star-spacing': 'off',
        // allow debugger during development
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        "no-tabs": "off",
        "eol-last": 0,//文件以单一的换行符结束
        "indent": 0,//缩进风格
        "no-undef": "off",//不能有未定义的变量
        "semi": 0,//语句强制分号结尾
        "arrow-parens": 0,//箭头函数用小括号括起来
        "key-spacing": [0, {"beforeColon": false, "afterColon": false}],//对象字面量中冒号的前后空格
        "arrow-spacing": 0,//=>的前/后括号
        "space-before-blocks": [0, "always"],//不以新行开始的块{前面要不要有空格
        "quotes": 0,//引号类型 `` "" ''
        "padded-blocks": 0,//块语句内行首行尾是否要空行
        "keyword-spacing": 0,
        "space-before-function-paren": 0,//函数定义时括号前面要不要有空格
        "spaced-comment": 0,//注释风格要不要有空格什么的
        "no-mixed-spaces-and-tabs": 0,//禁止混用tab和空格
        "one-var": 0,//连续声明
        "no-return-assign": 0,//return 语句中不能有赋值表达式
        "space-infix-ops": 0,//中缀操作符周围要不要有空格
        "no-new": 0,//禁止在使用new构造一个实例后不赋值
        "comma-spacing": 0,//逗号前后的空格
        "space-in-parens": 0,//小括号里面要不要有空格
        "no-useless-constructor": 0,
        "comma-dangle": 0,//对象字面量项尾不能有逗号
        "no-multi-spaces": 0,//不能用多余的空格
        "block-spacing": 0,
        "object-curly-spacing": 0,
        "no-useless-escape": 0,
        "no-use-before-define": 0,//未定义前不能使用
        "no-extend-native": 0,//禁止扩展native对象
        "eqeqeq": 0,//必须使用全等
        "no-useless-call": 0,//禁止不必要的call和apply
        "no-callback-literal":0,
        "no-unused-vars" : 0,
    }
}
