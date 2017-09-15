<!DOCTYPE html><!--  HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" --><html ng-app="someApp" id="ng-app" xmlns:ng="http://angularjs.org" lang="zh_CN" class="ng-scope"><head><style type="text/css">[uib-typeahead-popup].dropdown-menu{display:block;}</style><style type="text/css">.uib-time input{width:50px;}</style><style type="text/css">[uib-tooltip-popup].tooltip.top-left > .tooltip-arrow,[uib-tooltip-popup].tooltip.top-right > .tooltip-arrow,[uib-tooltip-popup].tooltip.bottom-left > .tooltip-arrow,[uib-tooltip-popup].tooltip.bottom-right > .tooltip-arrow,[uib-tooltip-popup].tooltip.left-top > .tooltip-arrow,[uib-tooltip-popup].tooltip.left-bottom > .tooltip-arrow,[uib-tooltip-popup].tooltip.right-top > .tooltip-arrow,[uib-tooltip-popup].tooltip.right-bottom > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.top-left > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.top-right > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.bottom-left > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.bottom-right > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.left-top > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.left-bottom > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.right-top > .tooltip-arrow,[uib-tooltip-html-popup].tooltip.right-bottom > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.top-left > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.top-right > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.bottom-left > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.bottom-right > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.left-top > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.left-bottom > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.right-top > .tooltip-arrow,[uib-tooltip-template-popup].tooltip.right-bottom > .tooltip-arrow,[uib-popover-popup].popover.top-left > .arrow,[uib-popover-popup].popover.top-right > .arrow,[uib-popover-popup].popover.bottom-left > .arrow,[uib-popover-popup].popover.bottom-right > .arrow,[uib-popover-popup].popover.left-top > .arrow,[uib-popover-popup].popover.left-bottom > .arrow,[uib-popover-popup].popover.right-top > .arrow,[uib-popover-popup].popover.right-bottom > .arrow,[uib-popover-html-popup].popover.top-left > .arrow,[uib-popover-html-popup].popover.top-right > .arrow,[uib-popover-html-popup].popover.bottom-left > .arrow,[uib-popover-html-popup].popover.bottom-right > .arrow,[uib-popover-html-popup].popover.left-top > .arrow,[uib-popover-html-popup].popover.left-bottom > .arrow,[uib-popover-html-popup].popover.right-top > .arrow,[uib-popover-html-popup].popover.right-bottom > .arrow,[uib-popover-template-popup].popover.top-left > .arrow,[uib-popover-template-popup].popover.top-right > .arrow,[uib-popover-template-popup].popover.bottom-left > .arrow,[uib-popover-template-popup].popover.bottom-right > .arrow,[uib-popover-template-popup].popover.left-top > .arrow,[uib-popover-template-popup].popover.left-bottom > .arrow,[uib-popover-template-popup].popover.right-top > .arrow,[uib-popover-template-popup].popover.right-bottom > .arrow{top:auto;bottom:auto;left:auto;right:auto;margin:0;}[uib-popover-popup].popover,[uib-popover-html-popup].popover,[uib-popover-template-popup].popover{display:block !important;}</style><style type="text/css">.uib-datepicker-popup.dropdown-menu{display:block;float:none;margin:0;}.uib-button-bar{padding:10px 9px 2px;}</style><style type="text/css">.uib-position-measure{display:block !important;visibility:hidden !important;position:absolute !important;top:-9999px !important;left:-9999px !important;}.uib-position-scrollbar-measure{position:absolute !important;top:-9999px !important;width:50px !important;height:50px !important;overflow:scroll !important;}.uib-position-body-scrollbar-measure{overflow:scroll !important;}</style><style type="text/css">.uib-datepicker .uib-title{width:100%;}.uib-day button,.uib-month button,.uib-year button{min-width:100%;}.uib-left,.uib-right{width:100%}</style><style type="text/css">.ng-animate.item:not(.left):not(.right){-webkit-transition:0s ease-in-out left;transition:0s ease-in-out left}</style><!--[if IE 8]><style>.ng-hide {display: none !important;}</style><![endif]--><style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}</style>
  <base href="/">
  <meta charset="utf-8">
  <!-- <meta http-equiv="Content-Type" content="text/html" charset="gb2312" > -->
  <!-- h5 -->
  <meta name="viewport" content="width=960px">
  <!--   <meta name="viewport" content="width=device-width"> -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <!--  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
 <meta name="format-detection" content="telephone=no"/> -->
  <!-- pc -->
  <meta http-equiv="content-type" content="text/html;charset=utf-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge;IE=8;Chrome=1;">
  <!--<meta content="always" name="referrer">-->
  <meta name="theme-color" content="#2932e1">
  <!--<META HTTP-EQUIV="pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
  <META HTTP-EQUIV="expires" CONTENT="0">-->
  <link rel="stylesheet" type="text/css" href="/main/css/stylesheets/swiper.css">
  <link rel="stylesheet" href="/main/styles/icons-files/icons.css">
  <style type="text/css">
    [ng\:cloak],
    [ng-cloak],
    [data-ng-cloak],
    [x-ng-cloak],
    .ng-cloak,
    .x-ng-cloak {
      display: none !important;
    }
  </style>
  <title ng-bind="title" class="ng-binding">我的账户_会员登录_账号登录-多融财富</title>
  <meta name="keywords" content="我的账户,会员登录,账号登录">
<meta name="description" content="通过登录我的账户，投资者可以直接进行投资，但是有必要提醒各位投资者，在设置密码时，千万不要使用简单的密码，避免他人破解而损失自己的利益。">
  <!-- <script src="https://api.html5media.info/1.1.4/html5media.min.js"></script> -->
  <script type="text/javascript" async="" src="http://static.mediav.com/mv.js"></script><script type="text/javascript" async="" src="http://material.mediav.com/bjjs/mba.js"></script><script src="https://hm.baidu.com/hm.js?f7c570071c5e4a0cf001fac7a2152cae"></script><script type="text/javascript" async="" src="http://static.mediav.com/mvl.js"></script><script type="text/javascript">
    (function () {
      var indexFile = (location.pathname.match(/\/(index[^\.]*\.html)/) || ['', ''])[1],
        rUrl = /(#!\/|api|guide|misc|tutorial|error|index[^\.]*\.html).*$/,
        baseUrl = location.href.replace(rUrl, indexFile),
        production = location.hostname === 'docs.angularjs.org',
        headEl = document.getElementsByTagName('head')[0],
        sync = true;

      var linkRoot = '/main/';
      var scriptRoot = '/main/vendor/';
      var jsRoot = '/main/js/';

      addTag('base', {
        href: baseUrl
      });

      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'css/dialog/ngDialog.min.css',
        type: 'text/css'
      });
      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'css/dialog/ngDialog-custom-width.css',
        type: 'text/css'
      });
      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'css/dialog/ngDialog-theme-default.min.css',
        type: 'text/css'
      });
      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'css/dialog/ngDialog-theme-plain.min.css',
        type: 'text/css'
      });
      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'css/stylesheets/screen.css',
        type: 'text/css'
      });
      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'style/stylesheets/screen.css',
        type: 'text/css'
      });
      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'css/jquery-ui.min.css',
        type: 'text/css'
      });
      addTag('link', {
        rel: 'stylesheet',
        href: linkRoot + 'css/animate.min.css',
        type: 'text/css'
      });

      // addTag('script', {src: scriptRoot + 'duorong-global.js'}, sync);


      addTag('script', {
        src: scriptRoot + 'es5-shim.min.js'
      }, sync); //兼容ie8
      addTag('script', {
        src: scriptRoot + 'prototype.js'
      }, sync); //面向对象
      addTag('script', {
        src: scriptRoot + 'jquery-1.11.1.min.js'
      }, sync);
      addTag('script', {
        src: scriptRoot + 'jquery.cookie.js'
      }, sync);
      addTag('script', {
        src: scriptRoot + 'angular.min.js'
      }, sync);
      addTag('script', {
        src: scriptRoot + 'slider.js'
      }, sync);
      addTag('script', {
        src: scriptRoot + 'rotate.js'
      }, sync); //jQ 旋转插件
      // addTag('script', {src: scriptRoot + 'jquery.mCustomScrollbar.js' }, sync);//滚动条
      addTag('script', {
        src: scriptRoot + 'swiper.min.js'
      }, sync);
      addTag('script', {
        src: scriptRoot + 'angular-ui-router.min.js'
      }, sync); //嵌套路由
      addTag('script', {
        src: scriptRoot + 'angular-css.min.js'
      }, sync); //嵌套路由
      addTag('script', {
        src: scriptRoot + 'angular-resource.min.js'
      }, sync); // 数据交互
      addTag('script', {
        src: scriptRoot + 'ngStorage.min.js'
      }, sync); //操作本地存储
      addTag('script', {
        src: scriptRoot + 'node_modules/angular-animate/angular-animate.min.js'
      }, sync);
      //addTag('script', {src: scriptRoot + 'node_modules/angular-ui-bootstrap/dist/ui-bootstrap.js' }, sync);
      //addTag('script', {src: 'https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-sanitize.js' }, sync);
      // addTag('script', {src: scriptRoot + 'node_modules/angular-ui-bootstrap/src/datepicker/datepicker.js' }, sync);  //angular 和bootstrap ui 组成的日期控件
      addTag('script', {
        src: scriptRoot + 'ui-bootstrap-tpls-2.4.0.min.js'
      }, sync);
      addTag('script', {
        src: scriptRoot + 'angular-sanitize.min.js'
      }, sync);
      addTag('script', {
        src: scriptRoot + 'node_modules/moment/min/moment.min.js'
      }, sync); //日期格式化，日期处理

      addTag('script', {
        src: scriptRoot + 'angular-locale_zh-cn.js'
      }, sync);

      addTag('script', {
        src: scriptRoot + 'qtip.js'
      }, sync); //  jq 的提示插件
      addTag('script', {
        src: scriptRoot + 'jquery.SuperSlide.2.1.1.js'
      }, sync); //滑动门
      addTag('script', {
        src: scriptRoot + 'highcharts.js'
      }, sync); //highCharts
      addTag('script', {
        src: scriptRoot + 'eahart.js'
      }, sync); //eahart
       addTag('script', {//三级联动1
        src: scriptRoot + 'Popt.js'
      }, sync);
      addTag('script', {//三级联动2
        src: scriptRoot + 'cityJson.js'
      }, sync);
      addTag('script', {//三级联动3
        src: scriptRoot + 'citySet.js'
      }, sync);


      addTag('script', {
        src: jsRoot + 'app.js'
      }, sync);

      addTag('script', {
        src: jsRoot + 'ng/dialog/ngDialog.js'
      }, sync); //angular的弹出框

      addTag('script', {
        src: jsRoot + 'ng/ngInvestList.js'
      }, sync);


      // controller
      addTag('script', {
        src: jsRoot + 'controllers/mainCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/dlCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/zhuceCtrl.js'
      }, sync);
      /*addTag('script', {
        src: jsRoot + 'controllers/homeCtrl.js'
      }, sync);*/
      addTag('script', {
        src:  '/main/activity/web/router.js?' + new Date().getTime()
      }, sync);
      /*addTag('script', {
        src: jsRoot + 'controllers/home.js'
      }, sync);*/
      addTag('script', {
        src: jsRoot + 'controllers/newZeroDetail.js' //新手灵活宝
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/bankBillCtrl.js' //p2p理财
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/bankCtrl.js' //定期理财
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/myCouPonCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/billDetailCtrl.js' //p2p理财产品详情
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/billDetail2Ctrl.js' //定期理财产品详情
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/AccountaccountHomeCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/resetPswCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/accountMyassetsWrapCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/accountMymsgWrapCtrl.js' //我的消息
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/accountMytaskWrapCtrl.js' //我的任务
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/myAccountmyInvestCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/securityCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/rechargeCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/rechargeSuccessCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/withdrawCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/successDialogCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/rechargeDialogCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/personalCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/sideMenuCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/jtMenuCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/helpCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/lizsCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/myFriend.js'
      }, sync);  
        addTag('script', {
        src: jsRoot + 'controllers/riskEvaluationCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/newsCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/friendCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/guideCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/sendCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/gsdtCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/protocolCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/cpaCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/yhtgCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/DocCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/doublegiftCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/guaranteeCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/loveCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/financialCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/loanProtocolCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/baoliProtocolCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/transferProtocolCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/pjSpecialCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/cp66Ctrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/inviteCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/olympiadCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/november.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/moonFestivalCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/cp157Ctrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/registerCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/activityCtrls/cp079Ctrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/xszyCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/bbyjCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/accountAutoBtCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'controllers/activityCtrls/returnMoneyCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'activity/web/doubleTwelveCtrl.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'activity/activityTime.js'
      }, sync);
      addTag('script', {
          src: jsRoot + 'activity/web/noviceOnlyEnjoyCtrl.js'
      }, sync);


      // controller over===================================================

      //directives
      addTag('script', {
        src: jsRoot + 'directives/myAccountDirective.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'directives/pointMallDirective.js'
      }, sync);
      addTag('script', {
          src: jsRoot + 'directives/escrowAccountDirective.js'
      }, sync);
      // directive over====================================================

      //data service
      addTag('script', {
        src: jsRoot + 'services/datas/pointMallDataService.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'services/datas/myAccountDataService.js'
      }, sync);

      addTag('script', {
        src: jsRoot + 'services/resourceService.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'services/filters/mapsFilters.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'services/filters/portFilters.js'
      }, sync);
      addTag('script', {
        src: jsRoot + 'services/filters/eorrFilters.js'
      }, sync);


      // window.console && window.console.info && window.console.info(Alphabet('DR','stereo'));

      function addTag(name, attributes, sync) {
        var el = document.createElement(name),
          attrName;

        for (attrName in attributes) {
          if (name == 'script') {
            el.setAttribute(attrName, attributes[attrName]); // + '?v=' + Math.random() * new Date());
          } else {
            el.setAttribute(attrName, attributes[attrName]);
          }
        }

        sync ? document.write(outerHTML(el)) : headEl.appendChild(el);
      }

      function outerHTML(node) {
        // if IE, Chrome take the internal method otherwise build one
        return node.outerHTML || (
          function (n) {
            var div = document.createElement('div'),
              h;
            div.appendChild(n);
            h = div.innerHTML;
            div = null;
            return h;
          })(node);
      }
    })();
  </script><base href="http://www.duorongcf.com/dl"><link rel="stylesheet" href="/main/css/dialog/ngDialog.min.css" type="text/css"><link rel="stylesheet" href="/main/css/dialog/ngDialog-custom-width.css" type="text/css"><link rel="stylesheet" href="/main/css/dialog/ngDialog-theme-default.min.css" type="text/css"><link rel="stylesheet" href="/main/css/dialog/ngDialog-theme-plain.min.css" type="text/css"><link rel="stylesheet" href="/main/css/stylesheets/screen.css" type="text/css"><link rel="stylesheet" href="/main/style/stylesheets/screen.css" type="text/css"><link rel="stylesheet" href="/main/css/jquery-ui.min.css" type="text/css"><link rel="stylesheet" href="/main/css/animate.min.css" type="text/css"><script src="/main/vendor/es5-shim.min.js"></script><script src="/main/vendor/prototype.js"></script><script src="/main/vendor/jquery-1.11.1.min.js"></script><script src="/main/vendor/jquery.cookie.js"></script><script src="/main/vendor/angular.min.js"></script><script src="/main/vendor/slider.js"></script><script src="/main/vendor/rotate.js"></script><script src="/main/vendor/swiper.min.js"></script><script src="/main/vendor/angular-ui-router.min.js"></script><script src="/main/vendor/angular-css.min.js"></script><script src="/main/vendor/angular-resource.min.js"></script><script src="/main/vendor/ngStorage.min.js"></script><script src="/main/vendor/node_modules/angular-animate/angular-animate.min.js"></script><script src="/main/vendor/ui-bootstrap-tpls-2.4.0.min.js"></script><script src="/main/vendor/angular-sanitize.min.js"></script><script src="/main/vendor/node_modules/moment/min/moment.min.js"></script><script src="/main/vendor/angular-locale_zh-cn.js"></script><script src="/main/vendor/qtip.js"></script><script src="/main/vendor/jquery.SuperSlide.2.1.1.js"></script><script src="/main/vendor/highcharts.js"></script><script src="/main/vendor/eahart.js"></script><script src="/main/vendor/Popt.js"></script><script src="/main/vendor/cityJson.js"></script><script src="/main/vendor/citySet.js"></script><script src="/main/js/app.js"></script><script src="/main/js/ng/dialog/ngDialog.js"></script><script src="/main/js/ng/ngInvestList.js"></script><script src="/main/js/controllers/mainCtrl.js"></script><script src="/main/js/controllers/dlCtrl.js"></script><script src="/main/js/controllers/zhuceCtrl.js"></script><script src="/main/activity/web/router.js?1505465362422"></script><script src="/main/js/controllers/newZeroDetail.js"></script><script src="/main/js/controllers/bankBillCtrl.js"></script><script src="/main/js/controllers/bankCtrl.js"></script><script src="/main/js/controllers/myCouPonCtrl.js"></script><script src="/main/js/controllers/billDetailCtrl.js"></script><script src="/main/js/controllers/billDetail2Ctrl.js"></script><script src="/main/js/controllers/AccountaccountHomeCtrl.js"></script><script src="/main/js/controllers/resetPswCtrl.js"></script><script src="/main/js/controllers/accountMyassetsWrapCtrl.js"></script><script src="/main/js/controllers/accountMymsgWrapCtrl.js"></script><script src="/main/js/controllers/accountMytaskWrapCtrl.js"></script><script src="/main/js/controllers/myAccountmyInvestCtrl.js"></script><script src="/main/js/controllers/securityCtrl.js"></script><script src="/main/js/controllers/rechargeCtrl.js"></script><script src="/main/js/controllers/rechargeSuccessCtrl.js"></script><script src="/main/js/controllers/withdrawCtrl.js"></script><script src="/main/js/controllers/successDialogCtrl.js"></script><script src="/main/js/controllers/rechargeDialogCtrl.js"></script><script src="/main/js/controllers/personalCtrl.js"></script><script src="/main/js/controllers/sideMenuCtrl.js"></script><script src="/main/js/controllers/jtMenuCtrl.js"></script><script src="/main/js/controllers/helpCtrl.js"></script><script src="/main/js/controllers/lizsCtrl.js"></script><script src="/main/js/controllers/myFriend.js"></script><script src="/main/js/controllers/riskEvaluationCtrl.js"></script><script src="/main/js/controllers/newsCtrl.js"></script><script src="/main/js/controllers/friendCtrl.js"></script><script src="/main/js/controllers/guideCtrl.js"></script><script src="/main/js/controllers/sendCtrl.js"></script><script src="/main/js/controllers/gsdtCtrl.js"></script><script src="/main/js/controllers/protocolCtrl.js"></script><script src="/main/js/controllers/cpaCtrl.js"></script><script src="/main/js/controllers/yhtgCtrl.js"></script><script src="/main/js/controllers/DocCtrl.js"></script><script src="/main/js/controllers/doublegiftCtrl.js"></script><script src="/main/js/controllers/guaranteeCtrl.js"></script><script src="/main/js/controllers/loveCtrl.js"></script><script src="/main/js/controllers/financialCtrl.js"></script><script src="/main/js/controllers/loanProtocolCtrl.js"></script><script src="/main/js/controllers/baoliProtocolCtrl.js"></script><script src="/main/js/controllers/transferProtocolCtrl.js"></script><script src="/main/js/controllers/pjSpecialCtrl.js"></script><script src="/main/js/controllers/cp66Ctrl.js"></script><script src="/main/js/controllers/inviteCtrl.js"></script><script src="/main/js/controllers/olympiadCtrl.js"></script><script src="/main/js/controllers/november.js"></script><script src="/main/js/controllers/moonFestivalCtrl.js"></script><script src="/main/js/controllers/cp157Ctrl.js"></script><script src="/main/js/controllers/registerCtrl.js"></script><script src="/main/js/controllers/activityCtrls/cp079Ctrl.js"></script><script src="/main/js/controllers/xszyCtrl.js"></script><script src="/main/js/controllers/bbyjCtrl.js"></script><script src="/main/js/controllers/accountAutoBtCtrl.js"></script><script src="/main/js/controllers/activityCtrls/returnMoneyCtrl.js"></script><script src="/main/js/activity/web/doubleTwelveCtrl.js"></script><script src="/main/js/activity/activityTime.js"></script><script src="/main/js/activity/web/noviceOnlyEnjoyCtrl.js"></script><script src="/main/js/directives/myAccountDirective.js"></script><script src="/main/js/directives/pointMallDirective.js"></script><script src="/main/js/directives/escrowAccountDirective.js"></script><script src="/main/js/services/datas/pointMallDataService.js"></script><script src="/main/js/services/datas/myAccountDataService.js"></script><script src="/main/js/services/resourceService.js"></script><script src="/main/js/services/filters/mapsFilters.js"></script><script src="/main/js/services/filters/portFilters.js"></script><script src="/main/js/services/filters/eorrFilters.js"></script>
  <script>
    document.createElement('ng-investlist');
    document.createElement('menu');
    document.createElement('my-banner');
    document.createElement('dr-new');
    document.createElement('dr-pg');
    document.createElement('my-newbanner');
    document.createElement('my-circle');
  </script>
  <style type="text/css">
    .summary-box {
      border: 1px solid #fe7634;
      margin-top: 50px;
      position: fixed;
      top: 50%;
      right: 10px;
      background: #fff;
      min-height: 100px;
      width: 300px;
      padding: 10px;
      overflow: hidden;
      height: auto !important;
      z-index: 16;
    }
    
    .summary-box h1,
    .summary-box p {
      line-height: 23px;
      font-size: 14px;
    }
    
    .summary-box h1,
    .summary-box a {
      font-weight: bold;
      color: #fe7634;
    }
    
    .summary-box a {
      padding: 0px;
      float: right;
    }
  </style>
<!-- ngRepeat: stylesheet in stylesheets | orderBy: 'weight' track by $index --><link ng-repeat="stylesheet in stylesheets | orderBy: 'weight' track by $index " rel="stylesheet" type="text/css" ng-href="/main/css/stylesheets/dl.css" ng-attr-media="{{ stylesheet.media }}" class="ng-scope" href="/main/css/stylesheets/dl.css"><!-- end ngRepeat: stylesheet in stylesheets | orderBy: 'weight' track by $index --></head>

<body><iframe src="http://ckmap.mediav.com/b?type=10&amp;jzqs=m-223965-0" id="mediav_cookiemapping" style="width: 1px; border: 0px; position: absolute; left: -100px; top: -100px; height: 1px;"></iframe>
  <div ng-show="maskHidde" class="page-loading ng-hide">
    <div class="">
      <div class="">
        <div class="">
          <div class="" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
        </div>
        <div style="width: 100%;height: 100%;position: fixed;top: 0;left: 0;background: none;z-index: 100;"></div>
        <p style="text-align:center;"><img src="images/loading.gif"></p>
      </div>
    </div>
  </div>
  <!-- uiView:  --><div ui-view="" class="ng-scope" style="height: 100%;"><!-- 登录 -->
<!-- tou -->
<div ng-controller="dlCtrl" class="ng-scope">
	<div class="loginpage">
		<div class="header-wrap">
			<div class="logo-wrap">
				<img src="/main/images/logo.png" ng-click="$state.go('main.home');">
				<span></span>
				<a target="_blank" style="display: block;float: left;cursor: default;"><img src="../../main/images/logoo.jpg" alt=""></a>
			</div>
		</div>
		<!-- <i class="bot"></i> -->
	</div>
	<!-- tou over -->

	<!-- zhong -->
	<div class="quick-register-wrap" style="min-height:596px;background-color: #EBF8FF;">
	  <div class="wqq-quick-register-wrap-box" style="width: 1200px;height: 578px;margin: 0 auto;position: relative;">
		<i class="register-banner" style="left:0"></i>
		<div class="quick-register-wrapper" style="padding: 20px 0 0 1140 px;float: left;position: absolute;right: 0;">
			<form class="login_form ng-pristine ng-untouched ng-invalid ng-invalid-required ng-valid-pattern ng-valid-minlength ng-valid-maxlength" ng-model="userLogin" name="userLoginform">
				<input style="display:none">
				<input type="password" style="display:none">
				<div class="quick-login-mod">
					<div class="title">
								<!-- <span class="choose-box">没有账号？
									<a href="" ng-click="onGotoLoginClick('zhuCe')" class="change-btn">快速注册</a>
								</span> -->
								<!-- <span class="choose-box">没有账号？
									<a href="{{hrefUrl}}zhuce" class="change-btn">快速注册</a>
								</span> -->
								<div class="throung"></div>
								<div class="p-box">
									<p class="">会员登录</p>
								</div>
							</div>
					<div class="quick-register-box">

						<!-- 登录用户名 -->  
						<div class="input-box">
							<div class="input-mod">
								<!--<input type="text" ng-pattern="/^1[3|4|5|7|8][0-9]{9}$/" placeholder="请输入手机号码"  ng-mouseOut="userNameShowError=true" ng-mouseOver="userNameShowError=fasle" onkeyup="this.value=this.value.replace(/[\D\.]/gi,'') " class="text"  ng-model="userLogin.mobilephone" name="userName" required minlength="11" maxlength="11" autocomplete="off" />-->
								<input type="text" ng-pattern="/^1[3|4|5|7|8][0-9]{9}$/" placeholder="请输入手机号码" ng-mouseout="userNameShowError=true" ng-mouseover="userNameShowError=fasle" onkeyup="this.value=this.value.replace(/[\D\.]/gi,'') " class="text ng-pristine ng-untouched ng-isolate-scope ng-invalid ng-invalid-required ng-valid-pattern ng-valid-minlength ng-valid-maxlength" ng-model="userLogin.mobilePhone" name="userName" required="" minlength="11" maxlength="11" autocomplete="off">
								<i ng-show="userLoginform.userName.$valid &amp;&amp; userNameShowError" ng-class="{false: 'common-form-ok', true: 'common-form-error'}[userLoginform.userName.$dirty || userLoginform.userName.$error.serverError]" class="ng-hide"></i>
								<span ng-show="userLoginform.userName.$valid &amp;&amp; userNameShowError" class="error ng-hide"><font color="red" class="ng-binding">请输入您的手机号</font></span>
							</div>
						</div>
						<!-- 图文验证 -->
						<div ng-show="isPicYanZhen" class="input-box ng-hide">
							<div class="img-box">
								<img ng-click="clickInput('changePic',$event)" src="http://www.duorongcf.com/login/validateCode.do?1505465363140">
							</div>
							<div class="input-mod">
								<input type="text" placeholder="请输入验证码" ng-pattern="/[a-zA-Z|0-9]{4}/" ng-maxlength="4" onkeyup="this.value=this.value.replace(/[^\d|a-zA-Z]/gi,'').substr(0,5)" ng-mouseover="picShowError=fasle" class="text small-text ng-pristine ng-untouched ng-valid ng-isolate-scope ng-valid-pattern ng-valid-maxlength" ng-model="userLogin.picCode" name="picCode" autocomplete="off">
							</div>
							<i ng-show="userLoginform.picCode.$valid &amp;&amp; picShowError" ng-class="{false: 'common-form-ok', true: 'common-form-error'}[userLoginform.picCode.$error.serverError]" class="ng-hide"></i>
							<span class="error ng-hide" ng-show="userLoginform.picCode.$valid &amp;&amp; picShowError"><font color="red" class="ng-binding"></font></span>
						</div>
						<!-- 密码 -->
						<div ng-hide="isShowUserPsw" class="input-box passwd-box">
							<div class="input-mod">
								<input placeholder="请输入密码" type="password" ng-model="userLogin.passWord" class="text ng-pristine ng-untouched ng-isolate-scope ng-invalid ng-invalid-required" name="passWord" autocomplete="off" required="">
							</div>
							<i ng-show="userLoginform.passWord.$valid" ng-class="{false: 'common-form-ok', true: 'common-form-error'}[userLoginform.passWord.$valid]" class="ng-hide common-form-ok"></i>
							<span class="error ng-hide" ng-show="userLoginform.passWord.$valid"><font color="red" class="ng-binding">密码必须6-18位数字和字母的组合</font></span>
						</div>
						<!-- 登录/注册 按钮 -->
						<div class="sub-box" ng-click="LoginClick('denLu',userLoginform)">
							<input style="background:#E8A22B;" ng-disabled="userLoginform.$invalid" class="sub-btn sub" type="submit" value="登 录" disabled="disabled">
						</div>
						<!-- 服务条款/切换状态 -->
						<div class="agreement-box">
							<div class="forget">
							    <span class="choose-box">没有账号？
									<a href="zhuce" class="change-btn">快速注册</a>
								</span>
								<a href="" ng-click="$state.go('main.resetPasswd')" class="reset-passwd">忘记密码</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	  </div>
	</div>
	<!-- zhong over -->

	<!-- buttom -->
	<div id="footer">
		<div class="footer-wrapper">
			<div class="foot-wrap">
				<div class="foot-nav">
					<div class="nav-mod">
						<!-- <i class="common-fot-home"></i> -->
						<p>关于多融</p>
						<ul>
							<li><a href="http://www.duorongcf.com/main/jt/DRGK?menuName=DRGK" target="_blank">多融概况</a></li>
							<!-- <li><a href="{{a|webIE8}}main/jt/FLFG?menuName=FLFG" target="_blank">法律法规</a></li> -->
							<li><a href="http://www.duorongcf.com/main/jt/LXWM?menuName=LXWM" target="_blank">联系我们</a></li>
							<li><a href="http://www.duorongcf.com/main/jt/guarantee?menuName=guarantee" target="_blank">安全保障</a></li>
						</ul>
					</div>
					<div class="nav-mod">
						<!-- <i class="common-fot-qa"></i> -->
						<p>帮助中心</p>
						<ul>
							<li><a href="http://www.duorongcf.com/main/jt2/help?menuName=help" target="_blank">常见问题</a></li>
							<li><a href="http://www.duorongcf.com/main/jt2/LCZS?menuName=LCZS" target="_blank">理财课堂</a></li>
							<li><a href="http://www.duorongcf.com/main/jt2/XSZY?menuName=XSZY" target="_blank">新手指引</a></li>
							<li><a href="http://www.duorongcf.com/main/FLFG" target="_blank">免责声明</a></li>
						</ul>
					</div>
				</div>
				<div class="modeline"></div>
				<div class="foot-code">
					<div class="mode">
						<div class="box">
							<img src="/main/images/common/footwechat.png">
						</div>
						<p>关注官方微信号<br>最新理财资讯，投资领先一步</p>
					</div>
					<div class="mode">
						<div class="box">
							<img src="/main/images/common/footapp.png">
						</div>
						<p>下载多融APP<br>开启理财护航之路</p>
					</div>
				</div>
				<div class="modeline"></div>                                                                
				<div class="contact-wrap">
					<div class="title">
						<p>客服热线</p>
					</div>
					<strong class="ng-binding">4006-908-896</strong>
					<span>工作时间:08:30 — 20:30<br>法定节假日除外<br>客服QQ:4006908896</span>
				</div>
			</div>
		</div>
		<div class="right-wrapper">
			<div class="foot-right">
				<!--<div class="right-text" style="padding: 0;">市场有风险 投资需谨慎；平台建议的预期年化收益率仅供参考，不代表未来实际收益</div>-->
				<div class="right-text" style="padding: 0;">市场有风险 投资需谨慎；平台建议的历史年化收益率仅供参考，不代表未来实际收益</div>
				<div class="right-text ng-binding" style="padding: 0;">沪ICP备15011207号-5 | Copyright Reserved © 上海银砖金融信息服务有限公司 v2.0.1</div>
				<div class="right-text" style="padding-top: 0;">公司电话：021-80312696 | 公司地址：上海市虹口区四川北路859号中信广场41楼</div>
				<ul>
					<li><a class="spread-partners-hu" href="https://trustsealinfo.verisign.com/splash?form_file=fdf/splash.fdf&amp;dn=www.duorongcf.com&amp;lang=zh_cn" target="_blank"><img src="/main/images/spread-partners/hu.png"></a></li>
					<li><a id="___szfw_logo___" class="spread-partners-kexin" href="https://credit.szfw.org/CX20170123033101030377.html" target="_blank"><img src="/main/images/spread-partners/kexin.png"></a></li>
					<li><a class="spread-partners-aliyun" href="https://www.aliyun.com/" target="_blank"><img src="/main/images/spread-partners/aliyun.png"></a></li>
					<li><a class="spread-partners-zdcy" href="https://www.yunaq.com/cyd/" target="_blank"><img src="/main/images/spread-partners/zdcy.png"></a></li>
					<li><a key="570e15dfefbfb02ee9d6f567" logo_size="83x30" logo_type="business" href="http://v.pinpaibao.com.cn/authenticate/cert/?site=www.duorongcf.com&amp;at=business" target="_black"><img style="background: #fff;" src="//static.anquan.org/static/outer/image/hy_83x30.png" alt="安全联盟认证" width="83" height="30"></a></li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">(function(){document.getElementById('___szfw_logo___').oncontextmenu = function(){return false;}})();</script>
	<!-- 底部结束 -->
  <!-- 弹框 -->
  <div class="mask ng-hide" ng-show="isShow">
    <div class="kong" style="width: 100%;
    height: 100%;
    position: fixed; 
    background-color: rgba(0, 0, 0, 0.6);
     background:#000\9;
    filter:alpha(opacity=60);
    top: 0;
    left: 0;"></div>
   <!--  中间内容部分 -->
      <div class="tan">
      		<div class="title">恭喜你获得</div>
      		<!-- ngRepeat: item in result -->
      		<div class="tishi ng-binding">
      			
      		</div>
      		<div class="button">
      			<a class="btnleft" ng-click="tiao()" target="_blank">积分兑换</a>
      			<a class="btnright" ng-click="close()" target="_blank">确定</a>
      		</div> 		
      </div>
      <div class="close" ng-click="close()"></div> 
      </div>
 
</div>

</div>
  <div class="mask-coupon ng-hide" ng-show="showMaskCoupon == true">
    <div class="coupon">
      <div class="close" ng-click="showMaskCoupon = false"></div>
      <a href="/main/bankBillList" ng-click="showMaskCoupon = false"></a>
    </div>
  </div>
  <script type="text/javascript">
    //
    var _mvq = _mvq || [];
    _mvq.push(['$setAccount', 'm-223965-0']);

    _mvq.push(['$logConversion']);
    (function () {
      var mvl = document.createElement('script');
      mvl.type = 'text/javascript';
      mvl.async = true;
      mvl.src = ('https:' == document.location.protocol ? 'https://static-ssl.mediav.com/mvl.js' : 'http://static.mediav.com/mvl.js');
      var s = document.getElementsByTagName('script')[0];
      s.parentNode.insertBefore(mvl, s);
    })();

    //百度统计
    var _hmt = _hmt || [];
    (function () {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?f7c570071c5e4a0cf001fac7a2152cae";
      var s = document.getElementsByTagName("script")[0];
      s.parentNode.insertBefore(hm, s);
    })();
  </script>



</body></html>