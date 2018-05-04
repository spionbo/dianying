// https://github.com/michael-ciniawsky/postcss-load-config

module.exports = {
    "plugins": {
        "postcss-partial-import": {},
        "postcss-url": {},
        // to edit target browsers: use "browserslist" field in package.json
        "autoprefixer": {},
        
        'postcss-pxtorem': {
            replace: true,
            propList: ['font', 'font-size', 'line-height', 'box-shadow',
                'letter-spacing', 'perspective', 'transform',
                '-webkit-perspective', '-webkit-transform',
                'max-width', 'min-width', 'max-height',
                'min-height', 'margin', 'margin-left',
                'margin-right', 'margin-bottom', 'margin-top',
                'padding', 'padding-left', 'padding-right',
                'padding-bottom', 'padding-top', 'border',
                'border-left', 'border-right', 'border-bottom',
                'border-top', 'width', 'height', 'background-position',
                'top', 'left', 'bottom', 'right',
                'border-radius'],
        },
        'postcss-extend': {},
        'precss': {},
    }
}
