<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>

<@common.page>
    <div>
        <@login.logout/>
        <span><a href="/user">User list</a> </span>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Enter the text: "/>
            <input type="text" name="tag" placeholder="Tag:"/>
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add</button>
        </form>
    </div>
    <div>List of Messages:</div>
    <div>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter!}">
            <button type="submit">Find</button>
        </form>
    </div>
    <#list messages as mess>
        <div>
            <b>${mess.id}</b>
            <span>${mess.text}</span>
            <i>${mess.tag}</i>
            <strong>${mess.authorName}</strong>
            <div>
                <#if mess.filename??>
                    <img src="/img/${mess.filename}">
                </#if>
            </div>
        </div>
    <#else >
        No messages!
    </#list>
</@common.page>