<style scoped>
    @import './menu.css';
</style>
<template>
    <article class="menu" :class="{cur:!show}">
        <div class="sidebar">
            <i class="icon" @click="setMenu"></i>
        </div>
        <div class="selectMenu">
            <select v-model="selected">
                <option v-for="item in permission" :key="item.id"
                    v-bind:value="item.id"
                >{{item.name}}</option>
            </select>
        </div>
        <ul class="page-menu">
            <li class="home">
                <a href="javascript:void(0)" >
                    <i class="fa fa-home"></i>
                    <span class="title">首页</span>
                    <span class="jian">
                        <i class="fa fa-caret-left"></i>
                    </span>
                </a>
            </li>
            <li v-for="item in permissions">
                <a href="javascript:void(0)" @click="goURL(item)" :data-url="item.url">
                    <i class="fa fa-file-text"></i>
                    <span class="title">{{item.name}}</span>
                    <span class="arrow">
                        <i class="fa fa-angle-right"></i>
                    </span>
                </a>
                <ul v-if="item.PermissionBean" class="sub-menu" >
                    <li v-for="obj in item.PermissionBean">
                        <a href="javascript:void(0)" @click="goURL(obj)">
                            <span class="title">{{obj.name}}</span>
                            <span class="arrow">
                                <i class="fa fa-angle-right"></i>
                            </span>
                        </a>
                        <ul v-if="obj.PermissionBean" class="sub-menu" >
                            <li v-for="item in obj.PermissionBean">
                                <a href="javascript:void(0)" @click="goURL(item)">
                                    <span class="title">{{item.name}}</span>
                                    <span class="arrow">
                                        <i class="fa fa-angle-right"></i>
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>

        </ul>
    </article>
</template>
<script>
	export default {
		props : {
        },
		data() {
			return {
				show : true, //是否展示栏目
				data : null,
                permission : [], //后台管理
                permissions : [], //后台管理
				menu : [],//栏目管理
				selected : null
            }
		},
        watch : {
            selected(val){
            	const self = this;
	            this.updateMenu();
            	self.permission.map(obj=>{
            		if(obj.id == val){
            			if(obj.url){
				            router.push(obj.url+'/list');
				            self.setMenuCurrent(obj.url+'/list');
			            }
		            }
	            });
                this.data.map(obj=>{
					if(obj.permission.id === val){
						self.permissions = obj.permissions;
					}
                });

            }
        },
		mounted() {
            const self = this;
            T.ajax({
                url : '/permission/currentMenuPermission',
                type : 'get'
            }).then(data=>{
            	self.data = data.data;
                data.data.map(obj=>{
                    self.permission.push(obj.permission);
                });
                self.selected = data.data[0].permission.id;
                self.permissions = data.data[0].permissions;
                this.updateMenu();
            });

            /*this.menu = [
                {
                	name : '栏目名称1',
	                subMenu : [
		                {
			                name : '栏目名称2',
			                link : 'http://www.baidu.com'
		                },
		                {
			                name : '栏目名称2',
			                link : 'http://www.baidu.com'
		                }
	                ]
                },
	            {
		            name : '栏目名称1',
                    subMenu : [
	                    {
		                    name : '栏目名称2',
		                    subMenu : [
			                    {
				                    name : '栏目名称3',
				                    link : 'http://www.baidu.com'
			                    },
			                    {
				                    name : '栏目名称3',
				                    link : 'http://www.baidu.com'
			                    },
			                    {
				                    name : '栏目名称3',
				                    link : 'http://www.baidu.com'
			                    }
		                    ]
	                    },
	                    {
		                    name : '栏目名称2',
		                    link : 'http://www.baidu.com'
	                    },
	                    {
		                    name : '栏目名称2',
		                    link : 'http://www.baidu.com'
	                    }
                    ]
	            },
	            {
		            name : '栏目名称1',
		            link : 'http://www.baidu.com',
		            subMenu : [
			            {
				            name : '栏目名称2',
				            link : 'http://www.baidu.com'
			            },
			            {
				            name : '栏目名称2',
				            link : 'http://www.baidu.com'
			            }
		            ]
	            }
            ];*/
            
		},
		methods : {
			updateMenu(){
				this.$nextTick(this.setTab);
			},
			setMenuCurrent( name ){
				this.$nextTick(function(){
					let taga = $(".page-menu>li>a");
					taga.each(function(i,ele){
						let url = $(this).attr("data-url");
						if(name == url){
							$(this).click();
						}
					});
				});

			},
			setMenu(){
                this.show = !this.show;
                //让收缩的菜单变成浮动
                let li = $('.page-menu > li');
                if(!this.show){
                    li.each(function( i,ele ){
                        if(ele.className.includes("open")){
                            $(this).addClass("hover");
                        }
                    })
                }else{
                    li.removeClass("hover");
                }

            },
			goURL( item ){
				router.push(item.url);
            },
            setMenuHeight(menu){
	            const self = this;
	            let submenu = $(menu).children('.sub-menu');
	            if(submenu.length){ //如果有子目录
                    let li = submenu.children('li');
                    $(submenu).css({height:'auto'});
		            submenu[0].height = (li.eq(0).height()+1)*li.length;
                    $(submenu).css({height:0});
                    self.setMenuHeight(li);
	            }
            },
			subMenu( menu , tagli ){
				const self = this;
				let submenu = $(tagli).children('.sub-menu');
				if(submenu.length){ //如果有子目录
					let li = submenu.children('li');
					li.each(function( i , ele ) {
						self.setMenushow(li,ele);
						self.subMenu(li,ele);
					});
				}
			},
            setTab(){
            	const self = this;
                let li = $('.page-menu > li');
	            li.each(function( i,ele ){
	                self.setMenushow(li,ele);
	                self.subMenu(li,ele);
	                $(this).click(function(){
                        if($(this).attr('class')=='home') return;
	                    if(self.show) return;
                        li.removeClass('hover');
                        $(this).addClass('hover');
                    });
                	//以下为获取高度
                    self.setMenuHeight(ele);
                })
            },
            setMenushow(li,tag ){
            	let self = $(tag),
                    taga = $(tag).children('a')[0];
                taga.isShow = false;
	            taga.onclick = function(){
                    this.isShow = !this.isShow;

                    //如果点了首页
                    if($(this).parent().attr('class')=='home') return;
                    //如果展开了
                    //if(tag.className.indexOf('open')>-1) return;

                    //清除其他tag ishow = false
                    self.parent().children("li").each(function(){
                        let a = $(this).children('a')[0];
                        if(a!== taga){
                            a.isShow = false;
                        }
                    });



		            let submenu = self.children('.sub-menu') ,
                        lisubmenu = li.children('.sub-menu');
		            li.removeClass("open");

		            //隐藏高度归0 方便transitoin
		            lisubmenu.each(function( i , ele ){
			            //if(ele == submenu[0]) return;
                        ele.height = $(ele).height();
                        $(ele).css({height:ele.height+'px'});
                        setTimeout(function(){
                            $(ele).css({height:0});
                        },50);
                    });

                    //展开菜单
                    if(this.isShow){
                        self.addClass("open");
                        if(submenu.length){
                            submenu.css({height:'auto'});
                            submenu[0].height = submenu.height();
                            submenu.css({height:'0'});
                            setTimeout(function(){
                                submenu.css({height:submenu[0].height+'px'});
                                setTimeout(function(){
                                    submenu.css({height:'auto'});
                                },300);
                            },50);
                        }
                    }


		            //清除导航右边的箭头
		            li.each(function(i , ele ){
			            if($(ele).attr('class')=='home') return;
			            $(ele).children('a').find('.fa').removeClass("fa-angle-down").addClass("fa-angle-right");
		            });
		            if(submenu.length){
                        if(this.isShow) {
                            $(this).find('.fa').removeClass("fa-angle-right").addClass("fa-angle-down");
                        }else{
                            $(this).find('.fa').removeClass("fa-angle-down").addClass("fa-angle-right");
                        }
		            }
	            };
            }
        }
	}
</script>