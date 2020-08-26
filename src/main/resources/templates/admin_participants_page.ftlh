<#import "containers/common.ftlh" as c>
<#import "./components/modals.ftlh" as modal>

<#function getUniversityById(universities, id)>
    <#list universities as university>
        <#if university.id==id>
            <#return university.abbreviation>
        </#if>
    </#list>
</#function>

<#function getCourseById(courses, id)>
    <#list courses as course>
        <#if course.id==id>
            <#return course.value>
        </#if>
    </#list>
</#function>

<#function getDirectionById(directions, id)>
    <#list directions as direction>
        <#if direction.id==id>
            <#return direction.name>
        </#if>
    </#list>
</#function>

<script>
    function toggleShow(id) {
        $('#' + id).collapse('toggle');
    }
</script>

<@c.page>
    <#include "components/navbar.ftlh">
    <#include "components/search.ftlh">

    <@modal.adminModal/>
    <@modal.infoModal/>
    <@modal.deleteStudentModal/>

    <div id="containerStudents" style="margin-top: 60px">

    </div>

<#--    <div style="margin-top: 60px">-->
<#--        <#list students as student>-->
<#--            <div class="card m-1 border-secondary" <#if student.id==80>focus</#if> >-->
<#--                <div class="card-header py-2" onclick="toggleShow(${student.id})">-->
<#--                    <div class="row align-items-center">-->
<#--                        <div id="studentId" hidden>${student.id}</div>-->
<#--                        <div class="col-1">-->
<#--                            <#if student.isValid>-->
<#--                                <img class="ml-1" src="/check.svg" width="20" height="20" alt="image description">-->
<#--                            <#else>-->
<#--                                <img class="ml-1" src="/question.svg" width="20" height="20" alt="image description">-->
<#--                            </#if>-->
<#--                        </div>-->
<#--                        <div class="col-3 text-truncate">-->
<#--                            ${student.surname + " " + student.name + " " + student.patronymic}-->
<#--                        </div>-->
<#--                        <div class="col-1 text-truncate">-->
<#--                            ${getCourseById(courses, student.courseId)}-->
<#--                        </div>-->
<#--                        <div class="col-1 text-truncate">-->
<#--                            ${getUniversityById(universities, student.universityId)}-->
<#--                        </div>-->
<#--                        <div class="col-1 text-truncate">-->
<#--                            ${getDirectionById(scienceDirections, student.scienceDirectionId)}-->
<#--                        </div>-->
<#--                        <div class="col-1 text-truncate">-->
<#--                            ${student.rating}-->
<#--                        </div>-->
<#--                        <div class="col-4 text-truncate">-->
<#--                            ${student.topic}-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--                <div class="collapse" id="${student.id}">-->
<#--                    <div class="card card-body py-2">-->
<#--                        <div class="row">-->
<#--                            <div class="btn-group ml-auto">-->
<#--                                <button type="button" class="btn btn-info"-->
<#--                                        onclick="showAdminStudentModal(${student.id}, false)">Полная информаци-->
<#--                                </button>-->
<#--                                <button type="button" class="btn btn-primary">Просмотр PDF</button>-->
<#--                                <button type="button" class="btn btn-success"-->
<#--                                        onclick="showAdminStudentModal(${student.id}, true)">Редактировать-->
<#--                                </button>-->
<#--                                <button type="button" class="btn btn-danger"-->
<#--                                        onclick="showDeleteStudentModal(${student.id})">Удалить-->
<#--                                </button>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--        </#list>-->
<#--    </div>-->

    <script type="text/javascript" src="/js/admin_participants_page/Queries.js"></script>
    <script type="text/javascript" src="/js/admin_participants_page/Handlers.js"></script>
    <script type="text/javascript" src="/js/admin_participants_page/Actions.js"></script>
    <script type="text/javascript" src="/js/admin_participants_page/List.js"></script>
    <script>showListParticipants();</script>

</@c.page>