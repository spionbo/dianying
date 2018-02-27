<#include "./components/head.ftl">
<div class="content-bg bg1">
	<div class="container img-size" data-paddingLeft="100" data-paddingRight="100" data-paddingTop="180" data-height="1015">
		<div class="main">
			<#list aboutList as item>
				<h2 class="title img-size" data-height="80" data-lineHeight="80">${item.title}</h2>
				<div class="content">
					${item.content}
				</div>
			</#list>
		</div>
	</div>
</div>
<#include "./components/footer.ftl">