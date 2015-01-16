package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

	private Connection conn;

	private Conexion(String conexion) throws SQLException {
		conn = DriverManager.getConnection(conexion);
		conn.setAutoCommit(true);
	}

	private Conexion(String conexion, String username, String password) throws SQLException {
		conn = DriverManager.getConnection(conexion, username, password);
		conn.setAutoCommit(true);
	}

	public static Conexion getInstance(Configuracion config) throws SQLException {
		if (config.isAnnonymousConnection())
			return new Conexion(config.getUrlSql() + config.getInstanciaSql());
		else
			return new Conexion(config.getUrlSql() + config.getInstanciaSql(), config.getUserSql(), config.getPassSql());
	}

	protected int executeInsert(String sql, boolean autoCommitRollback) {
		int row = 0;
		boolean ok = true;
		try {
			PreparedStatement pStatement = conn.prepareStatement(sql);
			row = pStatement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

			ok = false;
		} finally {
			if (autoCommitRollback) {
				if (!ok) {
					try {
						if (!conn.getAutoCommit())
							conn.rollback();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());
					} finally {
						try {
							conn.close();
						} catch (SQLException ex) {
							System.out.println("SQLException: " + ex.getMessage());
							System.out.println("SQLState: " + ex.getSQLState());
							System.out.println("VendorError: " + ex.getErrorCode());
						}
					}
				} else {
					try {
						if (!conn.getAutoCommit())
							conn.commit();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());

						try {
							if (!conn.getAutoCommit())
								conn.rollback();
						} catch (SQLException ex1) {
							System.out.println("SQLException: " + ex1.getMessage());
							System.out.println("SQLState: " + ex1.getSQLState());
							System.out.println("VendorError: " + ex1.getErrorCode());
						} finally {
							try {
								conn.close();
							} catch (SQLException ex2) {
								System.out.println("SQLException: " + ex2.getMessage());
								System.out.println("SQLState: " + ex2.getSQLState());
								System.out.println("VendorError: " + ex2.getErrorCode());
							}
						}
					}
				}
			}
		}
		return row;
	}

	protected int executeDelete(String sql, boolean autoCommitRollback) {
		int row = 0;
		boolean ok = true;
		try {
			PreparedStatement pStatement = conn.prepareStatement(sql);
			row = pStatement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

			ok = false;
		} finally {
			if (autoCommitRollback) {
				if (!ok) {
					try {
						if (!conn.getAutoCommit())
							conn.rollback();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());
					} finally {
						try {
							conn.close();
						} catch (SQLException ex) {
							System.out.println("SQLException: " + ex.getMessage());
							System.out.println("SQLState: " + ex.getSQLState());
							System.out.println("VendorError: " + ex.getErrorCode());
						}
					}
				} else {
					try {
						if (!conn.getAutoCommit())
							conn.commit();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());

						try {
							if (!conn.getAutoCommit())
								conn.rollback();
						} catch (SQLException ex1) {
							System.out.println("SQLException: " + ex1.getMessage());
							System.out.println("SQLState: " + ex1.getSQLState());
							System.out.println("VendorError: " + ex1.getErrorCode());
						} finally {
							try {
								conn.close();
							} catch (SQLException ex2) {
								System.out.println("SQLException: " + ex2.getMessage());
								System.out.println("SQLState: " + ex2.getSQLState());
								System.out.println("VendorError: " + ex2.getErrorCode());
							}
						}
					}
				}
			}
		}
		return row;
	}

	protected int executeUpdate(String sql, boolean autoCommitRollback) {
		int row = 0;
		boolean ok = true;
		try {
			PreparedStatement pStatement = conn.prepareStatement(sql);
			row = pStatement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

			ok = false;
		} finally {
			if (autoCommitRollback) {
				if (!ok) {
					try {
						if (!conn.getAutoCommit())
							conn.rollback();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());
					} finally {
						try {
							conn.close();
						} catch (SQLException ex) {
							System.out.println("SQLException: " + ex.getMessage());
							System.out.println("SQLState: " + ex.getSQLState());
							System.out.println("VendorError: " + ex.getErrorCode());
						}
					}
				} else {
					try {
						if (!conn.getAutoCommit())
							conn.commit();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());

						try {
							if (!conn.getAutoCommit())
								conn.rollback();
						} catch (SQLException ex1) {
							System.out.println("SQLException: " + ex1.getMessage());
							System.out.println("SQLState: " + ex1.getSQLState());
							System.out.println("VendorError: " + ex1.getErrorCode());
						} finally {
							try {
								conn.close();
							} catch (SQLException ex2) {
								System.out.println("SQLException: " + ex2.getMessage());
								System.out.println("SQLState: " + ex2.getSQLState());
								System.out.println("VendorError: " + ex2.getErrorCode());
							}
						}
					}
				}
			}
		}
		return row;
	}

	protected DataQRY executeSelect(String sql, boolean autoCommitRollback) throws ErrorGeneral {
		DataQRY dataQry = null;
		boolean ok = true;
		try {
			PreparedStatement pStatement = conn.prepareStatement(sql);
			ResultSet results = pStatement.executeQuery();
			dataQry = new DataQRY(results);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

			ok = false;
		} finally {
			if (autoCommitRollback) {
				if (!ok) {
					try {
						conn.rollback();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());
					} finally {
						try {
							conn.close();
						} catch (SQLException ex) {
							System.out.println("SQLException: " + ex.getMessage());
							System.out.println("SQLState: " + ex.getSQLState());
							System.out.println("VendorError: " + ex.getErrorCode());
						}
					}
				} else {
					try {
						if (!conn.getAutoCommit())
							conn.commit();
					} catch (SQLException ex) {
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());

						try {
							if (!conn.getAutoCommit())
								conn.rollback();
						} catch (SQLException ex1) {
							System.out.println("SQLException: " + ex1.getMessage());
							System.out.println("SQLState: " + ex1.getSQLState());
							System.out.println("VendorError: " + ex1.getErrorCode());
						} finally {
							try {
								conn.close();
							} catch (SQLException ex2) {
								System.out.println("SQLException: " + ex2.getMessage());
								System.out.println("SQLState: " + ex2.getSQLState());
								System.out.println("VendorError: " + ex2.getErrorCode());
							}
						}
					}
				}
			}
		}
		return dataQry;
	}

	protected int executeUpdate(String sql) {
		return executeUpdate(sql, true);
	}

	protected int executeDelete(String sql) {
		return executeUpdate(sql, true);
	}

	protected int executeInsert(String sql) {
		return executeInsert(sql, true);
	}

	protected DataQRY executeSelect(String sql) throws ErrorGeneral {
		return executeSelect(sql, true);
	}

	protected void commit() throws SQLException {
		conn.commit();
	}

	protected void rollback() throws SQLException {
		conn.rollback();
	}

	protected void close() throws SQLException {
		conn.close();
	}

	public boolean isOk() throws SQLException {
		return conn.isValid(5);
	}
}