/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipTestModule } from '../../../test.module';
import { LayslipRollDetailsDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details-delete-dialog.component';
import { LayslipRollDetailsService } from '../../../../../../main/webapp/app/entities/layslip-roll-details/layslip-roll-details.service';

describe('Component Tests', () => {

    describe('LayslipRollDetails Management Delete Component', () => {
        let comp: LayslipRollDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<LayslipRollDetailsDeleteDialogComponent>;
        let service: LayslipRollDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipRollDetailsDeleteDialogComponent],
                providers: [
                    LayslipRollDetailsService
                ]
            })
            .overrideTemplate(LayslipRollDetailsDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipRollDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipRollDetailsService);
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
