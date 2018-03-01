/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipTestModule } from '../../../test.module';
import { LayslipKeyHeaderDialogComponent } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header-dialog.component';
import { LayslipKeyHeaderService } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.service';
import { LayslipKeyHeader } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.model';
import { LayslipheaderService } from '../../../../../../main/webapp/app/entities/layslipheader';
import { LayslipRollDetailsService } from '../../../../../../main/webapp/app/entities/layslip-roll-details';

describe('Component Tests', () => {

    describe('LayslipKeyHeader Management Dialog Component', () => {
        let comp: LayslipKeyHeaderDialogComponent;
        let fixture: ComponentFixture<LayslipKeyHeaderDialogComponent>;
        let service: LayslipKeyHeaderService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipKeyHeaderDialogComponent],
                providers: [
                    LayslipheaderService,
                    LayslipRollDetailsService,
                    LayslipKeyHeaderService
                ]
            })
            .overrideTemplate(LayslipKeyHeaderDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipKeyHeaderDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipKeyHeaderService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LayslipKeyHeader(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.layslipKeyHeader = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'layslipKeyHeaderListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LayslipKeyHeader();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.layslipKeyHeader = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'layslipKeyHeaderListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
