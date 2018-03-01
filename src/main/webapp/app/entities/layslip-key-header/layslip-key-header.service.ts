import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { LayslipKeyHeader } from './layslip-key-header.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<LayslipKeyHeader>;

@Injectable()
export class LayslipKeyHeaderService {

    private resourceUrl =  SERVER_API_URL + 'api/layslip-key-headers';

    constructor(private http: HttpClient) { }

    create(layslipKeyHeader: LayslipKeyHeader): Observable<EntityResponseType> {
        const copy = this.convert(layslipKeyHeader);
        return this.http.post<LayslipKeyHeader>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(layslipKeyHeader: LayslipKeyHeader): Observable<EntityResponseType> {
        const copy = this.convert(layslipKeyHeader);
        return this.http.put<LayslipKeyHeader>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<LayslipKeyHeader>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<LayslipKeyHeader[]>> {
        const options = createRequestOption(req);
        return this.http.get<LayslipKeyHeader[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<LayslipKeyHeader[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: LayslipKeyHeader = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<LayslipKeyHeader[]>): HttpResponse<LayslipKeyHeader[]> {
        const jsonResponse: LayslipKeyHeader[] = res.body;
        const body: LayslipKeyHeader[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to LayslipKeyHeader.
     */
    private convertItemFromServer(layslipKeyHeader: LayslipKeyHeader): LayslipKeyHeader {
        const copy: LayslipKeyHeader = Object.assign({}, layslipKeyHeader);
        return copy;
    }

    /**
     * Convert a LayslipKeyHeader to a JSON which can be sent to the server.
     */
    private convert(layslipKeyHeader: LayslipKeyHeader): LayslipKeyHeader {
        const copy: LayslipKeyHeader = Object.assign({}, layslipKeyHeader);
        return copy;
    }
}
