package ma.enset.sma;
import jade.content.lang.Codec;
import jade.content.lang.rdf.RDFCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
public class BuyerAgentRdf extends Agent {
    private Ontology catalogOntology=CatalogOntology.getCatalogOntology();
    private RDFCodec rdfCodec =new RDFCodec();
    @Override
    protected void setup() {
        getContentManager().registerOntology(catalogOntology,CatalogOntology.ONTOLOGY_NAME);
        getContentManager().registerLanguage(rdfCodec);
        MessageTemplate messageTemplate=MessageTemplate.and
                (MessageTemplate.MatchOntology(CatalogOntology.ONTOLOGY_NAME),
                        MessageTemplate.MatchLanguage(rdfCodec.getName()));
        ACLMessage receivedMessage = blockingReceive(messageTemplate);
        try {
            Disponible disponible = (Disponible)getContentManager().extractContent(receivedMessage);
//                System.out.println(disponible);
            System.out.println(receivedMessage);
        } catch (Codec.CodecException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        }
    }
}