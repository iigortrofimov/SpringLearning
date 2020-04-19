<#include "security.ftlh">
<div class="card-columns">
    <#list messages as mess>
        <div class="card my-3">
            <#if mess.filename??>
                <img src="/img/${mess.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${mess.text}</span><br/>
                <i>#${mess.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-messages/${mess.author.id}"> ${mess.authorName}</a>
                <#if mess.author.id == currentUserId>
                    <a class="btn btn-dark" href="/user-messages/${mess.author.id}?message=${mess.id}"> Edit </a>
                    <a class="btn btn-dark" href="/user-messages/${mess.author.id}?message=${mess.id}&&isDelete=true"> Delete </a>
                </#if>
            </div>
        </div>
    <#else >
        No messages!
    </#list>
</div>