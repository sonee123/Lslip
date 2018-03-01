import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { WorkCenterMaster } from './work-center-master.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<WorkCenterMaster>;

@Injectable()
export class WorkCenterMasterService {

    private resourceUrl =  SERVER_API_URL + 'api/work-center-masters';

    constructor(private http: HttpClient) { }

    create(workCenterMaster: WorkCenterMaster): Observable<EntityResponseType> {
        const copy = this.convert(workCenterMaster);
        return this.http.post<WorkCenterMaster>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(workCenterMaster: WorkCenterMaster): Observable<EntityResponseType> {
        const copy = this.convert(workCenterMaster);
        return this.http.put<WorkCenterMaster>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<WorkCenterMaster>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<WorkCenterMaster[]>> {
        const options = createRequestOption(req);
        return this.http.get<WorkCenterMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<WorkCenterMaster[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: WorkCenterMaster = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<WorkCenterMaster[]>): HttpResponse<WorkCenterMaster[]> {
        const jsonResponse: WorkCenterMaster[] = res.body;
        const body: WorkCenterMaster[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to WorkCenterMaster.
     */
    private convertItemFromServer(workCenterMaster: WorkCenterMaster): WorkCenterMaster {
        const copy: WorkCenterMaster = Object.assign({}, workCenterMaster);
        return copy;
    }

    /**
     * Convert a WorkCenterMaster to a JSON which can be sent to the server.
     */
    private convert(workCenterMaster: WorkCenterMaster): WorkCenterMaster {
        const copy: WorkCenterMaster = Object.assign({}, workCenterMaster);
        return copy;
    }
}
