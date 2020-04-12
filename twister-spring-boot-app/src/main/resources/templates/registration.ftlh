<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>

<@common.page>
    <div class="mb-2 ml-3"> Add new user</div>
    ${message!}
    <@login.login "/registration" true/>
</@common.page>
