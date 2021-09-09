package org.lookaiz.services;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.format.ContentType;

import java.net.URI;


public class DataMovieService {//implements IDataMovieService {

    // TODO MMO : use config
    // OData V4
    private static final String serviceUrl_V2 = "https://data.sfgov.org/OData.svc/yitu-d5am";
    private static final String serviceUrl_V4 = "https://data.sfgov.org/api/odata/v4/yitu-d5am";

    public void getMovies() {

//        String serviceUrl = "http://localhost:9080/odata-server-sample/cars.svc";
        String entitySetName = "Movies";

        ODataClient client = ODataClientFactory.getClient();
        URI absoluteUri = client.newURIBuilder(serviceUrl_V4).appendEntitySetSegment(entitySetName).build();
        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request =
                client.getRetrieveRequestFactory().getEntitySetIteratorRequest(absoluteUri);

        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");

        ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();

        ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();

        while (iterator.hasNext()) {
            ClientEntity ce = iterator.next();
            System.out.println("Movie title: " + ce.getProperty("Title").getPrimitiveValue());
        }
    }

    public void getMovies2() {
        // Create OData client
        final ODataClient odataClient = ODataClientFactory.getClient();
        odataClient.getConfiguration().setDefaultPubFormat(ContentType.APPLICATION_JSON);

        // Read entity collection
        ODataEntitySetRequest<ClientEntitySet> request = odataClient.getRetrieveRequestFactory()
                .getEntitySetRequest(odataClient.newURIBuilder(serviceUrl_V4)
                        .appendEntitySetSegment("Manufacturers").build());
        final ODataRetrieveResponse<ClientEntitySet> response = request.execute();
        final ClientEntitySet entitySet = response.getBody();

        for(ClientEntity cEntity : entitySet.getEntities()) {
            System.out.println("Movie title : " + cEntity.getProperty("Title").getPrimitiveValue());
        }

        // Read entity
//        ODataEntityRequest<ClientEntity> request2 = odataClient.getRetrieveRequestFactory()
//                .getEntityRequest(odataClient.newURIBuilder(serviceUrl)
//                        .appendEntitySetSegment("Manufacturers").appendKeySegment(1).build());
//        final ODataRetrieveResponse<ClientEntity> response2 = request2.execute();
//        final ClientEntity entity = response2.getBody();

        // Read entity property
//        ODataPropertyRequest<ClientProperty> request = odataClient.getRetrieveRequestFactory()
//                .getPropertyRequest(odataClient.newURIBuilder(serviceUrl)
//                        .appendEntitySetSegment("Manufacturers").appendKeySegment(1)
//                        .appendPropertySegment("Name").build());
//        final ODataRetrieveResponse<ClientProperty> response = request.execute();
//        final ClientProperty property = response.getBody();
        // If property is a primitive type and if value has to be fetched
//        final ClientProperty property = property.get("Name");
//        final ClientPrimitiveValue clientValue = clientProperty.getPrimitiveValue();
        // If the property is a Complex Type and if value has to be fetched
        // Here Address is a complex property
//        final ClientComplexValue complexValue = prop.getComplexValue();
//        final ClientValue propertyComp = complexValue.get("Street").getValue();

    }


        public static void main(String[] args) {
        new DataMovieService().getMovies2();
    }


}
