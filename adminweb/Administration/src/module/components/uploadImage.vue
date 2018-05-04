<style>
</style>
<template>
	<div class="txt">
		<div class="edit">
			<div class="upload" @click="submit">
				<i class="fa fa-plus-square"></i>
				{{text}}
			</div>
			<div class="cnt" :class="{cur:error}">
				{{text}}
			</div>
			<input type="file"
			       class="file"
			       hidden
			       accept="image/png,image/gif,image/jpeg"
			       name="file"
			       @change='onUpload($event)'>
		</div>
		<div class="showUpload" v-if="imgUrl">
			<img :src="imgUrl"/>
		</div>
	</div>
</template>
<script>
	const ERROR = {
		default : {
			default : "请上传头像",
			error : "头像格示不对"
		}
	};
	export default {
		props:{
			text : String,
			dataType : String,
			prompt : String,
			name : String,
			placeholder : String,
			url : String,
		},
		data() {
			return {
				value : "",
				info : {},
				imgUrl : null,
				error : false
			}
		},
		watch : {
			imgUrl( val ){
				this.$store.commit("setCatch",{
					[this.name] : val
				})
			},
		},
		mounted() {
			if(this.url){
				this.imgUrl=this.url;
			}
			this.setMsg();
		},
		methods:{
			onUpload(e){
				let self = this ,
					formData = new FormData();
				formData.append('file', e.target.files[0]);
				let file = e.target.files[0],
					r = new FileReader();  //本地预览
				r.readAsDataURL(file);    //Base64
				r.onload = function(){
					self.ajax({
						url: "/upload/uploadImage",
						type: 'post',
						load : true,
						data: {
							"baseCode": r.result,
							"suffix": file.name.match(/\w+$/)[0], //"文件后缀png|jpg"
							"watermark": 0, //是否水印
							"width": 50, //需要压缩的长度 可不传
							"height": 50 //需要压缩的高度  可不传
						}
					}).then(data=>{
						self.imgUrl = data.data.imageUrl;
					});
				}
			},
			submit(){
				$(this.$el).find(".file").click();
			},
			setMsg(){
				try{
					this.info = ERROR[this.dataType||"default"];
				}catch(e){
					console.log("dataType类型出错");
				}

				this.text = this.prompt || this.info.default;
			}
		}
	}
	/**
	 * 后缀名       MIME名称
	 *.3gpp    audio/3gpp, video/3gpp
	 *.ac3    audio/ac3
	 *.asf       allpication/vnd.ms-asf
	 *.au           audio/basic
	 *.css           text/css
	 *.csv           text/csv
	 *.doc    application/msword
	 *.dot    application/msword
	 *.dtd    application/xml-dtd
	 *.dwg    image/vnd.dwg
	 *.dxf      image/vnd.dxf
	 *.gif            image/gif
	 *.htm    text/html
	 *.html    text/html
	 *.jp2            image/jp2
	 *.jpe       image/jpeg
	 *.jpeg    image/jpeg
	 *.jpg          image/jpeg
	 *.js       text/javascript, application/javascript
	 *.json    application/json
	 *.mp2    audio/mpeg, video/mpeg
	 *.mp3    audio/mpeg
	 *.mp4    audio/mp4, video/mp4
	 *.mpeg    video/mpeg
	 *.mpg    video/mpeg
	 *.mpp    application/vnd.ms-project
	 *.ogg    application/ogg, audio/ogg
	 *.pdf    application/pdf
	 *.png    image/png
	 *.pot    application/vnd.ms-powerpoint
	 *.pps    application/vnd.ms-powerpoint
	 *.ppt    application/vnd.ms-powerpoint
	 *.rtf            application/rtf, text/rtf
	 *.svf           image/vnd.svf
	 *.tif         image/tiff
	 *.tiff       image/tiff
	 *.txt           text/plain
	 *.wdb    application/vnd.ms-works
	 *.wps    application/vnd.ms-works
	 *.xhtml    application/xhtml+xml
	 *.xlc      application/vnd.ms-excel
	 *.xlm    application/vnd.ms-excel
	 *.xls           application/vnd.ms-excel
	 *.xlt      application/vnd.ms-excel
	 *.xlw      application/vnd.ms-excel
	 *.xml    text/xml, application/xml
	 *.zip            aplication/zip
	 *.xlsx     application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
	 */
</script>
