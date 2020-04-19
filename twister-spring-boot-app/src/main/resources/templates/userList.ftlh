<#import "parts/common.ftlh" as common>
<@common.page>

    List of Users:

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td> <#list user.roles as role> ${role} <#sep>, </#list> </td>
                <td><a  class="btn btn-dark" href="/user/${user.id}"> Edit</a></td>
                <td><a class="btn btn-dark" href="/user/${user.id}?isDelete=true"> Delete </a></td>

            </tr>
        </#list>
        </tbody>
    </table>
</@common.page>