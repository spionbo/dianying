<style scoped>
    .main{
        padding:15px;
    }
    .horizontal{
        .title{
            height:38px;
            line-height:38px;
            padding-left:15px;
            color:#333;
            background-color: #c9c9c9;
            border: 1px solid #ddd;
            border-bottom-color:#fff;
            background: linear-gradient(0, #eaebea 0%, #fff 100%);
        }
        .content{
            background-color: #fff;
            margin-bottom:10px;
            padding: 10px;
            border: 1px solid #ddd;
        }
        table{
            width:100%;
            th{
                font-size: 14px;
                font-weight: 600;
                padding: 8px;
                line-height: 20px;
                text-align: left;
            }
            td{
                padding: 8px;
                line-height: 20px;
                text-align: left;
                vertical-align: top;
                border-top: 1px solid #ddd;
            }
            tr:hover{
                td{
                    background-color: #f5f5f5;
                }
            }
        }
    }
    .form{
        .title{
            height:38px;
            line-height:38px;
            padding-left:15px;
            color:#fff;
            background-color: #e02222;
        }
        .content{
            background-color: #fff;
            margin-bottom:10px;
            padding: 10px;
            border: 1px solid #ef8476;
        }
    }
</style>
<template>
<article>
    <article class="main">
        <div class="horizontal">
            <div class="title">Simple Table</div>
            <div class="content">
                <table>
                    <tr>
                        <th>名称</th>
                        <th>名称</th>
                        <th>名称</th>
                        <th>名称</th>
                    </tr>
                    <tr>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                    </tr>
                    <tr>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="form">
            <div class="title">Simple Table</div>
            <div class="content">
                <table>
                    <tr>
                        <th>名称</th>
                        <th>名称</th>
                        <th>名称</th>
                        <th>名称</th>
                    </tr>
                    <tr>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                    </tr>
                    <tr>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                        <td>名称</td>
                    </tr>
                </table>
            </div>
        </div>
    </article>>
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