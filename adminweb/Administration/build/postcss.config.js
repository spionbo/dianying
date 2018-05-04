/**
 * postcss
 * created by bo.peng 2018/4/10
 * email:spion@qq.com
 */
module.exports = {
	importLoaders: 1,
	plugins: [
		require('postcss-partial-import'),
		require('autoprefixer'),
		require('postcss-pxtorem')({
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
				'top','left','bottom','right',
				'border-radius'],
		}),
		require('postcss-extend'),
		require('precss'),
	]
};