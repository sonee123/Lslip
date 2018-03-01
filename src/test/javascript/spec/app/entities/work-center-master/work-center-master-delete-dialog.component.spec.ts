/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipTestModule } from '../../../test.module';
import { WorkCenterMasterDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master-delete-dialog.component';
import { WorkCenterMasterService } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.service';

describe('Component Tests', () => {

    describe('WorkCenterMaster Management Delete Component', () => {
        let comp: WorkCenterMasterDeleteDialogComponent;
        let fixture: ComponentFixture<WorkCenterMasterDeleteDialogComponent>;
        let service: WorkCenterMasterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [WorkCenterMasterDeleteDialogComponent],
                providers: [
                    WorkCenterMasterService
                ]
            })
            .overrideTemplate(WorkCenterMasterDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkCenterMasterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkCenterMasterService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
