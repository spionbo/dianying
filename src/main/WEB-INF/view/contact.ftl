<#include "./components/head.ftl">
<div class="content-bg bg2">
	<div class="container img-size" data-paddingLeft="100" data-paddingRight="100" data-paddingTop="180" data-height="1015">
		<div class="contact">
			<h2 class="title img-size" data-height="80" data-lineHeight="80">联系方式</h2>
			<div class="content">
				<#list contactList as item>
					<div class="item">
						<div class="tit">${item.title}</div>
						<div class="cnt">${item.content}</div>
					</div>
				</#list>
			</div>
		</div>
	</div>
</div>
<#include "./components/footer.ftl">