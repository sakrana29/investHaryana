(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('assignServiceDialogController', assignServiceDialogController);

    assignServiceDialogController.$inject = ['$timeout','$scope','$stateParams','$uibModalInstance','projectAttachemnt','projectServiceLog','ProjectAttachemnt','ProjectServiceLog','FileManagement','Projectservicedetail','ProjectServiceReportInfoForClearance'];
    function assignServiceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, projectAttachemnt, projectServiceLog, ProjectAttachemnt, ProjectServiceLog, FileManagement, Projectservicedetail,ProjectServiceReportInfoForClearance) {
        var vm = this;
        vm.projectServiceLog = projectServiceLog;
        vm.projectAttachemnt = projectAttachemnt;
        vm.projectService = $stateParams.projectService;
        vm.projectservicereportinfo = ProjectServiceReportInfoForClearance.get({projectid:vm.projectService.projectid,department:vm.projectService.departmentName,service:vm.projectService.serviceName});
        var projectAttachmentResultObject=null;

        vm.clear = clear;
        vm.assignService = assignService;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function assignService(){
            vm.projectServiceLog.projectid=vm.projectService.projectid;
            vm.projectServiceLog.serviceid=vm.projectService.serviceid;
            ProjectServiceLog.save(vm.projectServiceLog,onServiceLogSaveSuccess,onServiceLogSaveError);
        }
        function onServiceLogSaveSuccess(result)
        {
//            alert('servicelogsaved');
            $scope.$emit('investhryApp:projectServiceLogUpdate', result);
            vm.projectService.isAssigned=true;
            vm.projectService.latestComments=vm.projectServiceLog.comments;
            vm.projectService.serviceFee=vm.projectservicedetail.serviceFee;
            Projectservicedetail.update(vm.projectService,onUpdateProjectServiceSuccess,onUpdateProjectServiceError);
        }
        function onUpdateProjectServiceSuccess(result)
        {
//            alert('projectservicedetail saved');
            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
            vm.projectservicereportinfo.assignDate=result.assigOnDate;
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
        function onUpdateProjectServiceReportInfoError()
        {
            vm.isSaving = false;
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
            vm.projectAttachemnt.description="Service Assign Attachment";
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
//            alert('file attachment updated');
            $uibModalInstance.close(result);
        }
        function onUpdateProjectAttachmentError()
        {
//            alert('file attachment not updated');
            vm.isSaving = false;
        }
    }
})();
