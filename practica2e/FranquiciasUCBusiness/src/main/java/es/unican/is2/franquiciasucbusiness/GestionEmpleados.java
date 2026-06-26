package es.unican.is2.franquiciasucbusiness;
import es.unican.is2.franquiciasuccommon.*;


public class GestionEmpleados implements IGestionEmpleados, IGestionAltasBajas {
    
    private ITiendasDAO tiendasDAO;
    private IEmpleadosDAO empleadosDAO;
    
    public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
        this.tiendasDAO = tiendasDAO;
        this.empleadosDAO = empleadosDAO;
    }

    @Override
    public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
        Tienda t = tiendasDAO.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        if (empleadosDAO.empleado(e.getDNI()) != null) {
            throw new OperacionNoValidaException("El empleado ya existe");
        }
        empleadosDAO.crearEmpleado(e);
        t.getEmpleados().add(e);
        tiendasDAO.modificarTienda(t);
        return e;
    }

    @Override
    public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
        Tienda t = tiendasDAO.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        Empleado e = empleadosDAO.empleado(dni);
        if (e == null) {
            return null;
        }
        // Verificar que pertenece a la tienda
        if (t.buscaEmpleado(dni) == null) {
            throw new OperacionNoValidaException("El empleado no pertenece a la tienda indicada");
        }
        empleadosDAO.eliminarEmpleado(dni);
        t.getEmpleados().remove(e);
        tiendasDAO.modificarTienda(t);
        
        return e;
    }

    @Override
    public boolean trasladarEmpleado(String dni, String actual, String destino) throws OperacionNoValidaException, DataAccessException {
        Tienda tActual = tiendasDAO.tiendaPorNombre(actual);
        Tienda tDestino = tiendasDAO.tiendaPorNombre(destino);
        if (tActual == null || tDestino == null) {
            return false;
        }
        Empleado e = empleadosDAO.empleado(dni);
        if (e == null) {
            throw new OperacionNoValidaException("Empleado no existe");
        }
        
        // Verificar que el empleado pertenece a la tienda actual
        if (tActual.buscaEmpleado(dni) == null) {
            throw new OperacionNoValidaException("El empleado no pertenece a la tienda actual");
        }
        
        tActual.getEmpleados().remove(e);
        tDestino.getEmpleados().add(e);
        
        tiendasDAO.modificarTienda(tActual);
        tiendasDAO.modificarTienda(tDestino);
        return true;
    }

    @Override
    public Empleado empleado(String dni) throws DataAccessException {
        return empleadosDAO.empleado(dni);
    }

    @Override
    public boolean bajaMedica(String dni) throws DataAccessException {
        Empleado e = empleadosDAO.empleado(dni);
        if (e != null) {
            e.darDeBaja();
            empleadosDAO.modificarEmpleado(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean altaMedica(String dni) throws DataAccessException {
        Empleado e = empleadosDAO.empleado(dni);
        if (e != null) {
            e.darDeAlta();
            empleadosDAO.modificarEmpleado(e);
            return true;
        }
        return false;
    }
}
