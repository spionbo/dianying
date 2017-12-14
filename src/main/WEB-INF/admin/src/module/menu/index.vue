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
                <option v-for="item in menu" :key="item.permission.id"
                    v-bind:value="item" :data-url="item.permission.url"
                >{{item.permission.name}}</option>
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
            <li v-for="item in permissionBeans">
                <a href="javascript:void(0)" @click="goURL(item)" :data-url="item.permission.url">
                    <i class="fa fa-file-text"></i>
                    <span class="title">{{item.permission.name}}</span>
                    <span class="arrow">
                        <i class="fa fa-angle-right"></i>
                    </span>
                </a>
                <ul v-if="item.permissionBeans" class="sub-menu" >
                    <li v-for="obj in item.permissionBeans">
                        <a href="javascript:void(0)" @click="goURL(obj)">
                            <span class="title">{{obj.permission.name}}</span>
                            <span class="arrow">
                                <i class="fa fa-angle-right"></i>
                            </span>
                        </a>
                        <ul v-if="obj.permissionBeans" class="sub-menu" >
                            <li v-for="item in obj.permissionBeans">
                                <a href="javascript:void(0)" @click="goURL(obj)">
                                    <span class="title">{{item.permission.name}}</span>
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
				menu : [],//栏目管理
				permissionBeans : [], //后台管理
				selected : null,
				selectObj : {}, //当前选择的栏目
            }
		},
        watch : {
            selected(obj){
            	if(obj==void 0) return;
	            this.permissionBeans = obj.permissionBeans;

	            //当点击时
				if(!obj.refresh){ //非刷新页面
					router.push(obj.permission.url);
					this.setMenuCurrent(obj.permission.url+'/list');
				}
            }
        },
		mounted() {
            const self = this;
            T.ajax({
                url : '/permission/currentMenuPermission',
                type : 'get'
            }).then(data=>{
            	self.data = data.data;
            	self.menu = data.data;
	            self.refresh();
            });
		},
		methods : {
			refresh(){
				let [self,href] = [this,window.location.href.match(/[^#]+$/)[0]];
				function setCurrent( data ){ //获取当前地址栏 对应的 对象
					let _obj;
					for(let i=0;i<data.length;i++){
						if(href.includes(data[i].permission.url)){
							_obj = data[i].permission;
							if(data[i].permissionBeans){
								return setCurrent(data[i].permissionBeans);
							}
						}
					};
					return _obj;
				}
				let obj = setCurrent(this.data);
				function getParent(data,parentId){ //获取当前对象的父对像
					let _obj;
					for(let i=0;i<data.length;i++){
						if(data[i].permission.id == parentId){
							_obj = data[i];
							break;
						}
					}
					if(!_obj){
						data.map(oj=>{
							getParent(obj.permissionBeans,parentId);
						})
					}
					return _obj;
				}
				if(obj){//如果获取到了，说明对应栏目是对的
					let selectObj;
					if(obj.parentId!=void 0){
						selectObj = getParent(this.data,obj.parentId);
					}else{
						selectObj = obj.id;
					}
					selectObj.refresh = true;
					self.selected = selectObj;
					self.setMenuCurrent(href);
				}else{
					self.selected = self.data[0];
				}
			},
			setMenuCurrent( name ){ //更新栏目样式
				let self = this;
				this.$nextTick(function(){
					self.setTab();
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
				console.log(item.permission.url);
				router.push(item.permission.url);
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