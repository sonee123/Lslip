/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipTestModule } from '../../../test.module';
import { LayslipGridDetailsDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details-delete-dialog.component';
import { LayslipGridDetailsService } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details.service';

describe('Component Tests', () => {

    describe('LayslipGridDetails Management Delete Component', () => {
        let comp: LayslipGridDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<LayslipGridDetailsDeleteDialogComponent>;
        let service: LayslipGridDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipGridDetailsDeleteDialogComponent],
                providers: [
                    LayslipGridDetailsService
                ]
            })
            .overrideTemplate(LayslipGridDetailsDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipGridDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipGridDetailsService);
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
