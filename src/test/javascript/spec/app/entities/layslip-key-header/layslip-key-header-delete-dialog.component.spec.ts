/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipTestModule } from '../../../test.module';
import { LayslipKeyHeaderDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header-delete-dialog.component';
import { LayslipKeyHeaderService } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.service';

describe('Component Tests', () => {

    describe('LayslipKeyHeader Management Delete Component', () => {
        let comp: LayslipKeyHeaderDeleteDialogComponent;
        let fixture: ComponentFixture<LayslipKeyHeaderDeleteDialogComponent>;
        let service: LayslipKeyHeaderService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipKeyHeaderDeleteDialogComponent],
                providers: [
                    LayslipKeyHeaderService
                ]
            })
            .overrideTemplate(LayslipKeyHeaderDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipKeyHeaderDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipKeyHeaderService);
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
