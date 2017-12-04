<style>
</style>
<template>
	<article>
		<div class="form add">
			<div class="title">新增栏目</div>
			<div class="content">
				<ul>
					<li>
						<div class="label">栏目名称</div>
						<div class="txt">
							<div class="edit"><input type="text" placeholder="栏目名称"></div>
							<div class="cnt">
								不超过5个中文。
							</div>
						</div>
					</li>
					<li>
						<div class="label">请选择栏目分类</div>
						<div class="txt">
							<div class="select">
								<select>
									<option>请选择分类</option>
									<option>视频</option>
									<option>小说</option>
								</select>
							</div>
							<div class="cnt">
								不选择的话，则该栏目为一级栏目
							</div>
						</div>
					</li>
					<li>
						<div class="label">路经</div>
						<div class="txt">
							<div class="edit"><input type="text" placeholder="路经名称"></div>
							<div class="cnt">
								例："/name"
							</div>
						</div>
					</li>
					<li>
						<div class="label">描述</div>
						<div class="txt">
							<div class="edit"><textarea class="textarea" style="width:500px;"></textarea></div>
							<div class="cnt">
								其他内容
							</div>
						</div>
					</li>

					<li>
						<div class="label">标题</div>
						<div class="txt">
							<div class="edit"><input type="radio"></div>
							<div class="cnt">
								其他内容
							</div>
						</div>
					</li>
					<li>
						<div class="label">标题</div>
						<div class="txt">
							<div class="edit"><input type="checkbox"></div>
							<div class="cnt">
								其他内容
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</article>
</template>
<script>
	export default {
		data() {
			return {}
		},
		mounted() {

		}
	}
</script>