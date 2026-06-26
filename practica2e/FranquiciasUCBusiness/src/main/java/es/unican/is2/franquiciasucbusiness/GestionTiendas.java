package es.unican.is2.franquiciasucbusiness;
import es.unican.is2.franquiciasuccommon.*;


public class GestionTiendas implements IGestionTiendas {
    
    private ITiendasDAO tiendasDAO;
    
    public GestionTiendas(ITiendasDAO tiendasDAO) {
        this.tiendasDAO = tiendasDAO;
    }

    @Override
    public Tienda nuevaTienda(Tienda c) throws DataAccessException {
        if (tiendasDAO.tiendaPorNombre(c.getNombre()) != null) {
            return null;
        }
        return tiendasDAO.crearTienda(c);
    }

    @Override
    public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
        Tienda t = tiendasDAO.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        if (t.getEmpleados() != null && !t.getEmpleados().isEmpty()) {
            throw new OperacionNoValidaException("No se puede eliminar una tienda con empleados");
        }
        return tiendasDAO.eliminarTienda(t.getId());
    }

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {
        return tiendasDAO.tiendaPorNombre(nombre);
    }
}
