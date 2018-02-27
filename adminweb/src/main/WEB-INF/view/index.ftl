<#include "./components/head.ftl">
<div class="content-bg">
    <div class="container img-size" data-paddingLeft="100" data-paddingRight="100" data-paddingTop="180" data-height="1015">
        <div class="main">
            <h2 class="title img-size" data-height="80" data-lineHeight="80">首页</h2>
            <div class="content">
                <#list mainList as item>
                    <p>${item.title}</p>
                </#list>
            </div>

        </div>
    </div>
</div>
<#include "./components/footer.ftl">