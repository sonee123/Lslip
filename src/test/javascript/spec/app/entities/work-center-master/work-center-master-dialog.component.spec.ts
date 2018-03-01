/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipTestModule } from '../../../test.module';
import { WorkCenterMasterDialogComponent } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master-dialog.component';
import { WorkCenterMasterService } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.service';
import { WorkCenterMaster } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.model';
import { LayslipKeyHeaderService } from '../../../../../../main/webapp/app/entities/layslip-key-header';

describe('Component Tests', () => {

    describe('WorkCenterMaster Management Dialog Component', () => {
        let comp: WorkCenterMasterDialogComponent;
        let fixture: ComponentFixture<WorkCenterMasterDialogComponent>;
        let service: WorkCenterMasterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [WorkCenterMasterDialogComponent],
                providers: [
                    LayslipKeyHeaderService,
                    WorkCenterMasterService
                ]
            })
            .overrideTemplate(WorkCenterMasterDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkCenterMasterDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkCenterMasterService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WorkCenterMaster(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.workCenterMaster = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'workCenterMasterListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WorkCenterMaster();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.workCenterMaster = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'workCenterMasterListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
