<#import "containers/common.ftlh" as c>
<#import "./components/modals.ftlh" as modals/>
<#import "./components/forms.ftlh" as forms/>

<@c.page>
    <#include "components/navbar.ftlh">
    <div class="container-fluid">
        <div class="d-flex justify-content-center">
            <div class='col-11 my-5'>
                <@forms.registrationForm/>
                <@modals.infoModal/>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="/js/registration_page/Queries.js"></script>
    <script type="text/javascript" src="/js/registration_page/Handlers.js"></script>
    <script type="text/javascript" src="/js/registration_page/Actions.js"></script>
</@c.page>