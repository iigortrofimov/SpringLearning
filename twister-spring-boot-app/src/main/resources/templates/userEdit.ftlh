<#import "parts/common.ftlh" as common>
<@common.page>
    User editor
    <form action="/user" method="post" xmlns="http://www.w3.org/1999/html">
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>
            <div>
                <label> <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}> ${role} </label>
            </div>
        </#list>

        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@common.page>