(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('deemedClearanceController', deemedClearanceController);

    deemedClearanceController.$inject = ['$timeout','$scope','$stateParams','$uibModalInstance','projectAttachemnt','projectServiceLog','ProjectAttachemnt','ProjectServiceLog','FileManagement','Projectservicedetail','ProjectServiceReportInfoForClearance'];
    function deemedClearanceController ($timeout, $scope, $stateParams, $uibModalInstance, projectAttachemnt, projectServiceLog, ProjectAttachemnt, ProjectServiceLog, FileManagement, Projectservicedetail,ProjectServiceReportInfoForClearance) {
        var vm = this;
        vm.projectServiceLog = projectServiceLog;
        vm.projectAttachemnt = projectAttachemnt;
        vm.projectService = $stateParams.projectService;
        vm.projectservicereportinfo = ProjectServiceReportInfoForClearance.get({projectid:vm.projectService.projectid,department:vm.projectService.departmentName,service:vm.projectService.serviceName});
        var projectAttachmentResultObject=null;

        vm.clear = clear;
        vm.deemedClearService = deemedClearService;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function deemedClearService(){
            vm.projectServiceLog.projectid=vm.projectService.projectid;
            vm.projectServiceLog.serviceid=vm.projectService.serviceid;
            ProjectServiceLog.save(vm.projectServiceLog,onServiceLogSaveSuccess,onServiceLogSaveError);
        }
        function onServiceLogSaveSuccess(result)
        {
//            alert('servicelogsaved');
            $scope.$emit('investhryApp:projectServiceLogUpdate', result);
            vm.projectService.latestComments=vm.projectServiceLog.comments
            vm.projectService.status="Deemed Cleared";
            Projectservicedetail.update(vm.projectService,onUpdateProjectServiceSuccess,onUpdateProjectServiceError);
        }
        function onUpdateProjectServiceSuccess(result)
        {
//            alert('projectservicedetail saved');
            $scope.$emit('investhryApp:projectservicedetailUpdate', result);

            vm.projectservicereportinfo.status='Deemed Cleared';
            ProjectServiceReportInfoForClearance.update(vm.projectservicereportinfo,onUpdateProjectServiceReportInfoSuccess,onUpdateProjectServiceReportInfoError);
        }
        function onUpdateProjectServiceReportInfoSuccess(result)
        {
            $scope.$emit('investhryApp:projectServiceReportInfoUpdate', result);
            if(angular.isDefined(vm.projectAttachemnt.file)){
                saveProjectAttachment();
            }
            else
            {
                $uibModalInstance.close(result);
                vm.isSaving = false;
            }
        }
        function onUpdateProjectServiceError()
        {
//            alert('project service not saved');
            vm.isSaving = false;
        }
        function onServiceLogSaveError()
        {
//            alert('Servicelog not saved');
            vm.isSaving = false;
        }
        function saveProjectAttachment()
        {
            var file=vm.projectAttachemnt.file;
            vm.projectAttachemnt.fileName=file.name;
            vm.projectAttachemnt.description="Deemed Clearance Attachment";
            vm.projectAttachemnt.fileExtension =file.type;
            ProjectAttachemnt.save(vm.projectAttachemnt,onSaveProjectAttachmentSuccess,onSaveProjectAttachmentError);
        }
        function onSaveProjectAttachmentSuccess(result)
        {
            $scope.$emit('investhryApp:projectAttachemntUpdate', result);
            var filename = result.id;
            var file =vm.projectAttachemnt.file;
            FileManagement.saveFile(file,filename);
            projectAttachmentResultObject=result;
            projectAttachmentResultObject.serverFileName=result.id;
            ProjectAttachemnt.update(projectAttachmentResultObject,onUpdateProjectAttachmentSuccess,onUpdateProjectAttachmentError);
        }
        function onSaveProjectAttachmentError()
        {
//            alert('project attachment not saved');
            vm.isSaving = false;
        }
        function onUpdateProjectAttachmentSuccess(result)
        {
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onUpdateProjectServiceReportInfoError()
        {
            vm.isSaving = false;
        }
        function onUpdateProjectAttachmentError()
        {
//            alert('file attachment not updated');
            vm.isSaving = false;
        }
    }
})();
