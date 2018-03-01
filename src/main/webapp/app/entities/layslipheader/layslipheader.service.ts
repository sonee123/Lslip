import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Layslipheader } from './layslipheader.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Layslipheader>;

@Injectable()
export class LayslipheaderService {

    private resourceUrl =  SERVER_API_URL + 'api/layslipheaders';

    constructor(private http: HttpClient) { }

    create(layslipheader: Layslipheader): Observable<EntityResponseType> {
        const copy = this.convert(layslipheader);
        return this.http.post<Layslipheader>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(layslipheader: Layslipheader): Observable<EntityResponseType> {
        const copy = this.convert(layslipheader);
        return this.http.put<Layslipheader>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Layslipheader>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Layslipheader[]>> {
        const options = createRequestOption(req);
        return this.http.get<Layslipheader[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Layslipheader[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Layslipheader = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Layslipheader[]>): HttpResponse<Layslipheader[]> {
        const jsonResponse: Layslipheader[] = res.body;
        const body: Layslipheader[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Layslipheader.
     */
    private convertItemFromServer(layslipheader: Layslipheader): Layslipheader {
        const copy: Layslipheader = Object.assign({}, layslipheader);
        return copy;
    }

    /**
     * Convert a Layslipheader to a JSON which can be sent to the server.
     */
    private convert(layslipheader: Layslipheader): Layslipheader {
        const copy: Layslipheader = Object.assign({}, layslipheader);
        return copy;
    }
}
