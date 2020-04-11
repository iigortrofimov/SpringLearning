<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>

<@common.page>
    <h2>Add new user</h2>
    ${message!}
    <@login.login "/registration" />
</@common.page>
